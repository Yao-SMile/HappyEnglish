package com.happyenglish.app.data.model

import kotlinx.serialization.Serializable

/**
 * 单词数据模型
 */
@Serializable
data class Word(
    val id: Int,
    val word: String,           // 单词
    val phonetic: String,       // 音标
    val meaning: String,        // 中文意思
    val example: String,        // 英文例句
    val exampleMeaning: String, // 例句翻译
    val unit: Int,              // 所属单元 (1-10)
    val difficulty: Int = 1,    // 难度等级 1-5
    val category: String = "default" // 分类
)

/**
 * 学习进度模型
 */
data class LearningProgress(
    val wordId: Int,
    var timesLearned: Int = 0,      // 学习次数
    var timesCorrect: Int = 0,      // 正确次数
    var timesWrong: Int = 0,        // 错误次数
    var lastReviewTime: Long = 0L,  // 最后复习时间
    var masteryLevel: Double = 0.0, // 掌握程度 0-100
    var nextReviewTime: Long = 0L   // 下次复习时间
) {
    // 计算掌握程度
    fun updateMastery() {
        val total = timesCorrect + timesWrong
        masteryLevel = if (total > 0) {
            (timesCorrect.toDouble() / total * 100)
        } else {
            0.0
        }
    }
    
    // 是否已掌握（正确率>80% 且学习次数>=3）
    fun isMastered(): Boolean {
        return masteryLevel >= 80 && timesLearned >= 3
    }
}

/**
 * AI 生成的题目模型
 */
data class AIQuestion(
    val id: String,
    val word: Word,
    val questionType: QuestionType,
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val difficulty: Int,
    val explanation: String = ""
)

/**
 * 题目类型枚举
 */
enum class QuestionType {
    MULTIPLE_CHOICE,      // 选择题（选中文意思）
    SPELLING,             // 拼写题
    LISTEN_AND_CHOOSE,    // 听音选词
    IMAGE_AND_CHOOSE,     // 看图选词
    FILL_IN_BLANK         // 填空题
}

/**
 * 学习统计模型
 */
data class LearningStats(
    val totalWords: Int = 0,
    val learnedWords: Int = 0,
    val masteredWords: Int = 0,
    val totalPractice: Int = 0,
    val correctAnswers: Int = 0,
    val streakDays: Int = 0,
    val totalStars: Int = 0,
    val lastStudyDate: Long = 0L
) {
    val accuracy: Double
        get() = if (totalPractice > 0) {
            correctAnswers.toDouble() / totalPractice * 100
        } else 0.0
}

/**
 * 成就徽章模型
 */
data class Achievement(
    val id: String,
    val name: String,
    val description: String,
    val icon: String,
    val requirement: Int,
    val isUnlocked: Boolean = false,
    val unlockDate: Long = 0L
)
