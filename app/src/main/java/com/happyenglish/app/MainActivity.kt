package com.happyenglish.app

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.happyenglish.app.ui.AIPracticeActivity
import com.happyenglish.app.ui.LearnActivity
import com.happyenglish.app.ui.ProgressActivity
import com.happyenglish.app.data.WordDatabase

/**
 * 主界面
 * 快乐学英语 - 小学四年级单词学习 APP
 */
class MainActivity : AppCompatActivity() {
    
    private lateinit var tvWelcome: TextView
    private lateinit var tvStats: TextView
    private lateinit var cardLearn: CardView
    private lateinit var cardAIPractice: CardView
    private lateinit var cardProgress: CardView
    private lateinit var cardSettings: CardView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initViews()
        setupClickListeners()
        updateStats()
    }
    
    private fun initViews() {
        tvWelcome = findViewById(R.id.tvWelcome)
        tvStats = findViewById(R.id.tvStats)
        cardLearn = findViewById(R.id.cardLearn)
        cardAIPractice = findViewById(R.id.cardAIPractice)
        cardProgress = findViewById(R.id.cardProgress)
        cardSettings = findViewById(R.id.cardSettings)
        
        // 设置欢迎语
        val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
        val greeting = when (hour) {
            in 0..5 -> "晚上好"
            in 6..11 -> "早上好"
            in 12..17 -> "下午好"
            else -> "晚上好"
        }
        tvWelcome.text = "$greeting，小朋友！👋"
    }
    
    private fun setupClickListeners() {
        // 开始学习
        cardLearn.setOnClickListener {
            animateCard(cardLearn)
            startActivity(Intent(this, LearnActivity::class.java))
        }
        
        // AI 智能练习
        cardAIPractice.setOnClickListener {
            animateCard(cardAIPractice)
            startActivity(Intent(this, AIPracticeActivity::class.java))
        }
        
        // 学习进度
        cardProgress.setOnClickListener {
            animateCard(cardProgress)
            startActivity(Intent(this, ProgressActivity::class.java))
        }
        
        // 设置
        cardSettings.setOnClickListener {
            animateCard(cardSettings)
            // TODO: 打开设置页面
        }
    }
    
    private fun animateCard(card: CardView) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.card_click)
        card.startAnimation(animation)
    }
    
    private fun updateStats() {
        val totalWords = WordDatabase.getTotalCount()
        tvStats.text = "共 $totalWords 个单词等你学习！\n今天也要加油哦！💪"
    }
    
    override fun onResume() {
        super.onResume()
        updateStats()
    }
}
