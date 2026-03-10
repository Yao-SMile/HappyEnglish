package com.happyenglish.app.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.happyenglish.app.R
import com.happyenglish.app.data.WordDatabase
import com.happyenglish.app.data.model.LearningProgress

/**
 * 学习进度界面
 * 显示学习统计和各单位进度
 */
class ProgressActivity : AppCompatActivity() {
    
    private lateinit var tvTotalWords: TextView
    private lateinit var tvLearnedWords: TextView
    private lateinit var tvMasteredWords: TextView
    private lateinit var tvAccuracy: TextView
    private lateinit var tvStreak: TextView
    private lateinit var cardUnit1: CardView
    private lateinit var cardUnit2: CardView
    private lateinit var cardUnit3: CardView
    private lateinit var cardUnit4: CardView
    private lateinit var cardUnit5: CardView
    private lateinit var cardUnit6: CardView
    
    // 模拟学习进度（实际应从数据库读取）
    private val progressMap = mutableMapOf<Int, LearningProgress>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)
        
        initViews()
        updateStats()
        updateUnitProgress()
    }
    
    private fun initViews() {
        tvTotalWords = findViewById(R.id.tvTotalWords)
        tvLearnedWords = findViewById(R.id.tvLearnedWords)
        tvMasteredWords = findViewById(R.id.tvMasteredWords)
        tvAccuracy = findViewById(R.id.tvAccuracy)
        tvStreak = findViewById(R.id.tvStreak)
        cardUnit1 = findViewById(R.id.cardUnit1)
        cardUnit2 = findViewById(R.id.cardUnit2)
        cardUnit3 = findViewById(R.id.cardUnit3)
        cardUnit4 = findViewById(R.id.cardUnit4)
        cardUnit5 = findViewById(R.id.cardUnit5)
        cardUnit6 = findViewById(R.id.cardUnit6)
    }
    
    private fun updateStats() {
        val totalWords = WordDatabase.getTotalCount()
        val learnedWords = progressMap.size
        val masteredWords = progressMap.count { it.value.isMastered() }
        
        tvTotalWords.text = totalWords.toString()
        tvLearnedWords.text = learnedWords.toString()
        tvMasteredWords.text = masteredWords.toString()
        
        // 模拟数据
        tvAccuracy.text = "85%"
        tvStreak.text = "3 天"
    }
    
    private fun updateUnitProgress() {
        // Unit 1
        updateUnitCard(cardUnit1, 1, "My Classroom", 10)
        // Unit 2
        updateUnitCard(cardUnit2, 2, "My Schoolbag", 10)
        // Unit 3
        updateUnitCard(cardUnit3, 3, "My Friends", 10)
        // Unit 4
        updateUnitCard(cardUnit4, 4, "My Home", 10)
        // Unit 5
        updateUnitCard(cardUnit5, 5, "Dinner's Ready", 10)
        // Unit 6
        updateUnitCard(cardUnit6, 6, "Meet My Family", 10)
    }
    
    private fun updateUnitCard(card: CardView, unit: Int, name: String, wordCount: Int) {
        // 计算该单元的掌握程度
        val unitWords = WordDatabase.getWordsByUnit(unit)
        val learnedInUnit = unitWords.count { progressMap.containsKey(it.id) }
        val masteredInUnit = unitWords.count { progressMap[it.id]?.isMastered() == true }
        val progress = if (wordCount > 0) learnedInUnit * 100 / wordCount else 0
        
        // 这里只是示例，实际应该动态更新 UI
        // 简化处理，只显示单元信息
    }
}
