package com.happyenglish.app.ai

import com.happyenglish.app.data.WordDatabase
import com.happyenglish.app.data.model.AIQuestion
import com.happyenglish.app.data.model.LearningProgress
import com.happyenglish.app.data.model.QuestionType
import com.happyenglish.app.data.model.Word
import kotlin.random.Random

/**
 * AI 动态出题引擎
 * 根据学习进度智能生成题目，难度自适应
 */
class AIQuestionGenerator {
    
    private val database = WordDatabase
    
    // 难度配置
    private val difficultyConfig = mapOf(
        1 to 4,   // 难度 1: 4 个选项
        2 to 4,   // 难度 2: 4 个选项
        3 to 5,   // 难度 3: 5 个选项
        4 to 5,   // 难度 4: 5 个选项
        5 to 6    // 难度 5: 6 个选项
    )
    
    /**
     * 生成一套练习题
     * @param progressMap 学习进度
     * @param count 题目数量
     * @param targetUnit 目标单元（0 表示混合）
     */
    fun generatePractice(
        progressMap: Map<Int, LearningProgress>,
        count: Int = 10,
        targetUnit: Int = 0
    ): List<AIQuestion> {
        val questions = mutableListOf<AIQuestion>()
        val wordsToPractice = selectWordsToPractice(progressMap, count, targetUnit)
        
        wordsToPractice.forEachIndexed { index, word ->
            val difficulty = calculateDifficulty(word, progressMap[word.id])
            val questionType = selectQuestionType(difficulty, index)
            val question = generateQuestion(word, questionType, difficulty)
            questions.add(question)
        }
        
        return questions
    }
    
    /**
     * 选择要练习的单词
     * 优先选择：未掌握的、错误率高的、长时间未复习的
     */
    private fun selectWordsToPractice(
        progressMap: Map<Int, LearningProgress>,
        count: Int,
        targetUnit: Int
    ): List<Word> {
        var availableWords = if (targetUnit > 0) {
            database.getWordsByUnit(targetUnit)
        } else {
            database.allWords
        }
        
        // 计算每个单词的优先级分数
        val wordScores = availableWords.map { word ->
            val progress = progressMap[word.id]
            val score = calculatePriorityScore(word, progress)
            word to score
        }
        
        // 按优先级排序，优先选择分数高的（需要练习的）
        val sorted = wordScores.sortedByDescending { it.second }
        
        // 选择前 count 个单词
        return sorted.take(count).map { it.first }
    }
    
    /**
     * 计算单词优先级分数
     * 分数越高越需要练习
     */
    private fun calculatePriorityScore(word: Word, progress: LearningProgress?): Double {
        if (progress == null) {
            // 未学习过的单词，优先级中等
            return 50.0
        }
        
        var score = 0.0
        
        // 掌握程度越低，优先级越高（0-100 分）
        score += (100 - progress.masteryLevel) * 0.5
        
        // 错误率越高，优先级越高
        val total = progress.timesCorrect + progress.timesWrong
        if (total > 0) {
            val errorRate = progress.timesWrong.toDouble() / total
            score += errorRate * 30
        }
        
        // 长时间未复习，优先级提高
        val daysSinceReview = (System.currentTimeMillis() - progress.lastReviewTime) / (1000 * 60 * 60 * 24)
        if (daysSinceReview > 3) {
            score += daysSinceReview * 5
        }
        
        // 学习次数少，优先级提高
        if (progress.timesLearned < 3) {
            score += 20
        }
        
        return score
    }
    
    /**
     * 计算题目难度
     */
    private fun calculateDifficulty(word: Word, progress: LearningProgress?): Int {
        // 基础难度由单词本身决定
        var difficulty = word.difficulty
        
        // 根据学习进度调整
        if (progress != null) {
            if (progress.masteryLevel > 80) {
                // 已掌握的单词，提高难度
                difficulty = minOf(5, difficulty + 1)
            } else if (progress.masteryLevel < 40) {
                // 掌握度低的单词，降低难度
                difficulty = maxOf(1, difficulty - 1)
            }
        }
        
        return difficulty
    }
    
    /**
     * 选择题目类型
     * 根据难度和学习阶段选择合适的题型
     */
    private fun selectQuestionType(difficulty: Int, questionIndex: Int): QuestionType {
        return when {
            // 简单难度：选择题为主
            difficulty <= 2 -> {
                when (questionIndex % 3) {
                    0 -> QuestionType.MULTIPLE_CHOICE
                    1 -> QuestionType.IMAGE_AND_CHOOSE
                    else -> QuestionType.LISTEN_AND_CHOOSE
                }
            }
            // 中等难度：混合题型
            difficulty <= 3 -> {
                when (questionIndex % 4) {
                    0 -> QuestionType.MULTIPLE_CHOICE
                    1 -> QuestionType.LISTEN_AND_CHOOSE
                    2 -> QuestionType.FILL_IN_BLANK
                    else -> QuestionType.IMAGE_AND_CHOOSE
                }
            }
            // 高难度：增加拼写题
            else -> {
                when (questionIndex % 4) {
                    0 -> QuestionType.SPELLING
                    1 -> QuestionType.FILL_IN_BLANK
                    2 -> QuestionType.MULTIPLE_CHOICE
                    else -> QuestionType.LISTEN_AND_CHOOSE
                }
            }
        }
    }
    
    /**
     * 生成具体题目
     */
    private fun generateQuestion(
        word: Word,
        questionType: QuestionType,
        difficulty: Int
    ): AIQuestion {
        return when (questionType) {
            QuestionType.MULTIPLE_CHOICE -> generateMultipleChoice(word, difficulty)
            QuestionType.SPELLING -> generateSpelling(word, difficulty)
            QuestionType.LISTEN_AND_CHOOSE -> generateListenAndChoose(word, difficulty)
            QuestionType.IMAGE_AND_CHOOSE -> generateImageAndChoose(word, difficulty)
            QuestionType.FILL_IN_BLANK -> generateFillInBlank(word, difficulty)
        }
    }
    
    /**
     * 生成选择题（看英文选中文）
     */
    private fun generateMultipleChoice(word: Word, difficulty: Int): AIQuestion {
        val optionCount = difficultyConfig[difficulty] ?: 4
        val wrongOptions = generateWrongMeanings(word.meaning, optionCount - 1)
        val options = (wrongOptions + word.meaning).shuffled()
        
        return AIQuestion(
            id = "mc_${word.id}_${System.currentTimeMillis()}",
            word = word,
            questionType = QuestionType.MULTIPLE_CHOICE,
            question = "单词 \"${word.word}\" 的意思是？",
            options = options,
            correctAnswer = word.meaning,
            difficulty = difficulty,
            explanation = "${word.word} ${word.phonetic} ${word.meaning}"
        )
    }
    
    /**
     * 生成拼写题
     */
    private fun generateSpelling(word: Word, difficulty: Int): AIQuestion {
        return AIQuestion(
            id = "sp_${word.id}_${System.currentTimeMillis()}",
            word = word,
            questionType = QuestionType.SPELLING,
            question = "拼写单词：${word.meaning}",
            options = emptyList(),
            correctAnswer = word.word.lowercase(),
            difficulty = difficulty,
            explanation = "${word.word} ${word.phonetic} ${word.meaning}"
        )
    }
    
    /**
     * 生成听音选词题
     */
    private fun generateListenAndChoose(word: Word, difficulty: Int): AIQuestion {
        val optionCount = difficultyConfig[difficulty] ?: 4
        val wrongWords = generateWrongWords(word, optionCount - 1)
        val options = (wrongWords + word.word).shuffled()
        
        return AIQuestion(
            id = "lc_${word.id}_${System.currentTimeMillis()}",
            word = word,
            questionType = QuestionType.LISTEN_AND_CHOOSE,
            question = "听音选词：（点击播放按钮听发音）",
            options = options,
            correctAnswer = word.word,
            difficulty = difficulty,
            explanation = "${word.word} ${word.phonetic} ${word.meaning}"
        )
    }
    
    /**
     * 生成看图选词题
     */
    private fun generateImageAndChoose(word: Word, difficulty: Int): AIQuestion {
        val optionCount = difficultyConfig[difficulty] ?: 4
        val wrongWords = generateWrongWords(word, optionCount - 1)
        val options = (wrongWords + word.word).shuffled()
        
        return AIQuestion(
            id = "ic_${word.id}_${System.currentTimeMillis()}",
            word = word,
            questionType = QuestionType.IMAGE_AND_CHOOSE,
            question = "看图选词：（图片：${word.meaning}）",
            options = options,
            correctAnswer = word.word,
            difficulty = difficulty,
            explanation = "${word.word} ${word.phonetic} ${word.meaning}"
        )
    }
    
    /**
     * 生成填空题
     */
    private fun generateFillInBlank(word: Word, difficulty: Int): AIQuestion {
        val sentence = word.example
        val blankedSentence = sentence.replace(word.word, "_____", ignoreCase = true)
        
        // 生成干扰选项
        val optionCount = difficultyConfig[difficulty] ?: 4
        val wrongWords = generateWrongWords(word, optionCount - 1)
        val options = (wrongWords + word.word).shuffled()
        
        return AIQuestion(
            id = "fb_${word.id}_${System.currentTimeMillis()}",
            word = word,
            questionType = QuestionType.FILL_IN_BLANK,
            question = "完成句子：$blankedSentence\n提示：${word.exampleMeaning}",
            options = options,
            correctAnswer = word.word,
            difficulty = difficulty,
            explanation = "${word.word} ${word.phonetic} ${word.meaning}\n例句：${word.example}"
        )
    }
    
    /**
     * 生成干扰选项（中文意思）
     */
    private fun generateWrongMeanings(correctMeaning: String, count: Int): List<String> {
        val allMeanings = database.allWords
            .map { it.meaning }
            .filter { it != correctMeaning }
            .distinct()
        return allMeanings.shuffled().take(count)
    }
    
    /**
     * 生成干扰选项（英文单词）
     */
    private fun generateWrongWords(correctWord: Word, count: Int): List<String> {
        val allWords = database.allWords
            .filter { it.id != correctWord.id }
            .map { it.word }
            .distinct()
        return allWords.shuffled().take(count)
    }
    
    /**
     * 根据答题情况调整后续难度
     */
    fun adjustDifficulty(currentDifficulty: Int, isCorrect: Boolean): Int {
        return if (isCorrect) {
            // 答对了，适当提高难度
            minOf(5, currentDifficulty + 1)
        } else {
            // 答错了，适当降低难度
            maxOf(1, currentDifficulty - 1)
        }
    }
    
    /**
     * 生成智能复习建议
     */
    fun generateReviewSuggestion(progressMap: Map<Int, LearningProgress>): String {
        val weakWords = progressMap.filter { it.value.masteryLevel < 60 }
        val unlearnedWords = database.allWords.filter { it.id !in progressMap.keys }
        
        val suggestions = mutableListOf<String>()
        
        if (weakWords.isNotEmpty()) {
            suggestions.add("📚 有 ${weakWords.size} 个单词需要加强复习")
        }
        
        if (unlearnedWords.isNotEmpty()) {
            suggestions.add("📖 还有 ${unlearnedWords.size} 个新单词等待学习")
        }
        
        if (progressMap.values.any { it.isMastered() }) {
            val mastered = progressMap.values.count { it.isMastered() }
            suggestions.add("🎉 已掌握 $mastered 个单词，继续加油！")
        }
        
        return if (suggestions.isEmpty()) {
            "🌟 太棒了！所有单词都已掌握！"
        } else {
            suggestions.joinToString("\n")
        }
    }
}
