package com.happyenglish.app.ui

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.happyenglish.app.R
import com.happyenglish.app.ai.AIQuestionGenerator
import com.happyenglish.app.data.model.AIQuestion
import com.happyenglish.app.data.model.LearningProgress
import com.happyenglish.app.data.model.QuestionType
import java.util.*

/**
 * AI 智能练习界面
 * 动态出题，难度自适应
 */
class AIPracticeActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    
    private lateinit var questionGenerator: AIQuestionGenerator
    private lateinit var tts: TextToSpeech
    
    // UI 组件
    private lateinit var tvQuestionNumber: TextView
    private lateinit var tvDifficulty: TextView
    private lateinit var tvProgress: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var btnPlaySound: ImageButton
    private lateinit var layoutOptions: LinearLayout
    private lateinit var cardFeedback: CardView
    private lateinit var tvFeedback: TextView
    private lateinit var tvExplanation: TextView
    private lateinit var btnNext: Button
    private lateinit var progressBar: ProgressBar
    
    // 数据
    private var questions = listOf<AIQuestion>()
    private var currentIndex = 0
    private var score = 0
    private var currentDifficulty = 2
    private lateinit var progressMap: MutableMap<Int, LearningProgress>
    
    // 配置
    private val totalQuestions = 10
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_practice)
        
        initTTS()
        questionGenerator = AIQuestionGenerator()
        progressMap = mutableMapOf()
        
        initViews()
        generateNewQuestions()
    }
    
    private fun initTTS() {
        tts = TextToSpeech(this, this)
    }
    
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.US
        }
    }
    
    private fun initViews() {
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber)
        tvDifficulty = findViewById(R.id.tvDifficulty)
        tvProgress = findViewById(R.id.tvProgress)
        tvQuestion = findViewById(R.id.tvQuestion)
        btnPlaySound = findViewById(R.id.btnPlaySound)
        layoutOptions = findViewById(R.id.layoutOptions)
        cardFeedback = findViewById(R.id.cardFeedback)
        tvFeedback = findViewById(R.id.tvFeedback)
        tvExplanation = findViewById(R.id.tvExplanation)
        btnNext = findViewById(R.id.btnNext)
        progressBar = findViewById(R.id.progressBar)
        
        btnPlaySound.setOnClickListener {
            playCurrentWordSound()
        }
        
        btnNext.setOnClickListener {
            nextQuestion()
        }
    }
    
    private fun generateNewQuestions() {
        questions = questionGenerator.generatePractice(
            progressMap = progressMap,
            count = totalQuestions
        )
        currentIndex = 0
        score = 0
        currentDifficulty = 2
        showQuestion()
    }
    
    private fun showQuestion() {
        if (currentIndex >= questions.size) {
            showResult()
            return
        }
        
        val question = questions[currentIndex]
        
        // 更新进度
        tvQuestionNumber.text = "第 ${currentIndex + 1} / $totalQuestions 题"
        tvDifficulty.text = "难度：${"⭐".repeat(currentDifficulty)}"
        tvProgress.text = "得分：$score"
        progressBar.progress = (currentIndex.toFloat() / totalQuestions * 100).toInt()
        
        // 显示题目
        tvQuestion.text = question.question
        
        // 显示/隐藏播放按钮
        btnPlaySound.visibility = if (question.questionType == QuestionType.LISTEN_AND_CHOOSE) {
            View.VISIBLE
        } else {
            View.GONE
        }
        
        // 生成选项
        generateOptions(question)
        
        // 隐藏反馈
        cardFeedback.visibility = View.GONE
    }
    
    private fun generateOptions(question: AIQuestion) {
        layoutOptions.removeAllViews()
        
        if (question.options.isEmpty()) {
            // 拼写题，显示输入框
            val editText = EditText(this).apply {
                hint = "输入单词..."
                textSize = 18f
                setPadding(32, 32, 32, 32)
            }
            
            val button = Button(this).apply {
                text = "提交"
                setOnClickListener {
                    val answer = editText.text.toString().trim().lowercase()
                    checkAnswer(answer, question)
                }
            }
            
            layoutOptions.addView(editText)
            layoutOptions.addView(button)
        } else {
            // 选择题，显示选项按钮
            question.options.forEach { option ->
                val button = Button(this).apply {
                    text = option
                    textSize = 16f
                    setPadding(32, 24, 32, 24)
                    setOnClickListener {
                        checkAnswer(option, question)
                    }
                }
                
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 0, 16)
                }
                
                layoutOptions.addView(button, params)
            }
        }
    }
    
    private fun playCurrentWordSound() {
        val question = questions[currentIndex]
        tts.speak(question.word.word, TextToSpeech.QUEUE_FLUSH, null, null)
    }
    
    private fun checkAnswer(answer: String, question: AIQuestion) {
        val isCorrect = answer.equals(question.correctAnswer, ignoreCase = true)
        
        // 更新分数
        if (isCorrect) {
            score += 10
        }
        
        // 更新学习进度
        updateProgress(question.word.id, isCorrect)
        
        // 显示反馈
        showFeedback(isCorrect, question)
        
        // 调整难度
        currentDifficulty = questionGenerator.adjustDifficulty(currentDifficulty, isCorrect)
        
        // 禁用选项按钮
        disableOptions()
    }
    
    private fun updateProgress(wordId: Int, isCorrect: Boolean) {
        val progress = progressMap.getOrPut(wordId) {
            LearningProgress(wordId = wordId)
        }
        
        progress.timesLearned++
        if (isCorrect) {
            progress.timesCorrect++
        } else {
            progress.timesWrong++
        }
        progress.lastReviewTime = System.currentTimeMillis()
        progress.updateMastery()
    }
    
    private fun showFeedback(isCorrect: Boolean, question: AIQuestion) {
        cardFeedback.visibility = View.VISIBLE
        
        if (isCorrect) {
            tvFeedback.text = "✅ 太棒了！答对了！"
            tvFeedback.setTextColor(getColor(android.R.color.holo_green_dark))
        } else {
            tvFeedback.text = "❌ 再加油！正确答案是：${question.correctAnswer}"
            tvFeedback.setTextColor(getColor(android.R.color.holo_red_dark))
        }
        
        tvExplanation.text = question.explanation
    }
    
    private fun disableOptions() {
        for (i in 0 until layoutOptions.childCount) {
            val view = layoutOptions.getChildAt(i)
            view.isEnabled = false
        }
        btnNext.visibility = View.VISIBLE
    }
    
    private fun nextQuestion() {
        currentIndex++
        btnNext.visibility = View.GONE
        showQuestion()
    }
    
    private fun showResult() {
        // 创建结果对话框
        val dialog = AlertDialog.Builder(this)
            .setTitle("🎉 练习完成！")
            .setMessage("得分：$score / ${totalQuestions * 10}\n正确率：${score * 10}%\n\n${questionGenerator.generateReviewSuggestion(progressMap)}")
            .setPositiveButton("再来一次") { _, _ ->
                generateNewQuestions()
            }
            .setNegativeButton("返回") { _, _ ->
                finish()
            }
            .setCancelable(false)
            .create()
        
        dialog.show()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        tts.stop()
        tts.shutdown()
    }
}
