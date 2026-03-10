package com.happyenglish.app.ui

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.happyenglish.app.R
import com.happyenglish.app.data.WordDatabase
import com.happyenglish.app.data.model.Word
import java.util.*

/**
 * 单词学习界面
 * 按单元学习单词
 */
class LearnActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    
    private lateinit var tts: TextToSpeech
    private lateinit var spinnerUnit: Spinner
    private lateinit var tvWord: TextView
    private lateinit var tvPhonetic: TextView
    private lateinit var tvMeaning: TextView
    private lateinit var tvExample: TextView
    private lateinit var tvExampleMeaning: TextView
    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button
    private lateinit var btnPlaySound: ImageButton
    private lateinit var tvProgress: TextView
    
    private var currentUnit = 1
    private var currentIndex = 0
    private var currentWords = listOf<Word>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn)
        
        initTTS()
        initViews()
        setupUnitSpinner()
        loadUnitWords(1)
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
        spinnerUnit = findViewById(R.id.spinnerUnit)
        tvWord = findViewById(R.id.tvWord)
        tvPhonetic = findViewById(R.id.tvPhonetic)
        tvMeaning = findViewById(R.id.tvMeaning)
        tvExample = findViewById(R.id.tvExample)
        tvExampleMeaning = findViewById(R.id.tvExampleMeaning)
        btnPrevious = findViewById(R.id.btnPrevious)
        btnNext = findViewById(R.id.btnNext)
        btnPlaySound = findViewById(R.id.btnPlaySound)
        tvProgress = findViewById(R.id.tvProgress)
        
        btnPlaySound.setOnClickListener {
            playCurrentWordSound()
        }
        
        btnPrevious.setOnClickListener {
            previousWord()
        }
        
        btnNext.setOnClickListener {
            nextWord()
        }
    }
    
    private fun setupUnitSpinner() {
        val units = WordDatabase.getAllUnits()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units.map { "Unit $it" })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerUnit.adapter = adapter
        
        spinnerUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                currentUnit = units[position]
                loadUnitWords(currentUnit)
            }
            
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    
    private fun loadUnitWords(unit: Int) {
        currentWords = WordDatabase.getWordsByUnit(unit)
        currentIndex = 0
        showWord()
    }
    
    private fun showWord() {
        if (currentWords.isEmpty()) return
        
        val word = currentWords[currentIndex]
        
        tvWord.text = word.word
        tvPhonetic.text = word.phonetic
        tvMeaning.text = word.meaning
        tvExample.text = "\"${word.example}\""
        tvExampleMeaning.text = word.exampleMeaning
        
        tvProgress.text = "${currentIndex + 1} / ${currentWords.size}"
        
        btnPrevious.isEnabled = currentIndex > 0
        btnNext.isEnabled = currentIndex < currentWords.size - 1
    }
    
    private fun previousWord() {
        if (currentIndex > 0) {
            currentIndex--
            showWord()
        }
    }
    
    private fun nextWord() {
        if (currentIndex < currentWords.size - 1) {
            currentIndex++
            showWord()
        }
    }
    
    private fun playCurrentWordSound() {
        if (currentWords.isNotEmpty()) {
            val word = currentWords[currentIndex]
            tts.speak(word.word, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        tts.stop()
        tts.shutdown()
    }
}
