# 🎓 快乐学英语 - 小学四年级单词学习 APP

一款专为小学四年级学生设计的英语单词学习应用，支持**AI 动态出题**和**难度自适应**。

![Android](https://img.shields.io/badge/Android-5.0+-green.svg)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-purple.svg)
![SDK](https://img.shields.io/badge/SDK-21%2B-blue.svg)

---

## ✨ 核心功能

### 📚 单词学习
- 人教版四年级上下册词库（100+ 核心单词）
- 图片 + 单词 + 音标 + 发音 + 例句
- TTS 语音朗读支持
- 按单元分类学习

### 🤖 AI 智能出题
- **动态生成题目**：根据学习进度智能选题
- **难度自适应**：答对提高难度，答错降低难度
- **多种题型**：
  - 选择题（看英文选中文）
  - 拼写题（听写单词）
  - 听音选词（练听力）
  - 看图选词（形象记忆）
  - 填空题（语境应用）

### 📊 学习追踪
- 学习进度统计
- 掌握程度分析（0-100%）
- 错题本自动记录
- 智能复习建议

### 🎮 游戏化学习
- 闯关模式（10 个单元）
- 积分奖励系统
- 连续学习打卡
- 成就徽章系统

---

## 🏗️ 技术架构

```
app/
├── src/main/
│   ├── java/com/happyenglish/app/
│   │   ├── data/
│   │   │   ├── model/          # 数据模型
│   │   │   │   ├── Word.kt     # 单词、进度、题目模型
│   │   │   └── WordDatabase.kt # 词库数据
│   │   ├── ai/
│   │   │   └── AIQuestionGenerator.kt  # AI 出题引擎
│   │   ├── ui/
│   │   │   ├── LearnActivity.kt        # 学习界面
│   │   │   ├── AIPracticeActivity.kt   # AI 练习界面
│   │   │   └── ProgressActivity.kt     # 进度界面
│   │   └── MainActivity.kt             # 主界面
│   ├── res/
│   │   ├── layout/           # 布局文件
│   │   ├── drawable/         # 图形资源
│   │   ├── anim/             # 动画资源
│   │   └── values/           # 字符串、颜色、主题
│   └── AndroidManifest.xml
├── build.gradle.kts
└── settings.gradle.kts
```

---

## 🚀 快速开始

### 环境要求
- Android Studio Hedgehog (2023.1.1) 或更高版本
- JDK 17
- Android SDK 21+

### 构建步骤

1. **克隆/打开项目**
   ```bash
   cd HappyEnglishApp
   ```

2. **在 Android Studio 中打开**
   - File → Open → 选择 `HappyEnglishApp` 文件夹

3. **同步 Gradle**
   - 等待 Gradle 同步完成

4. **运行应用**
   - 连接 Android 设备或启动模拟器
   - 点击 Run 按钮

### 构建 APK
```bash
./gradlew assembleDebug
```

APK 输出位置：`app/build/outputs/apk/debug/app-debug.apk`

---

## 🎯 AI 出题引擎说明

### 工作原理

```kotlin
// 1. 选择要练习的单词
// 优先选择：未掌握的、错误率高的、长时间未复习的
val wordsToPractice = selectWordsToPractice(progressMap, count)

// 2. 计算每个单词的难度
// 根据单词本身难度 + 学习进度调整
val difficulty = calculateDifficulty(word, progress)

// 3. 选择合适的题型
// 简单：选择题为主；困难：增加拼写题
val questionType = selectQuestionType(difficulty)

// 4. 生成具体题目
// 自动生成干扰选项
val question = generateQuestion(word, questionType, difficulty)
```

### 难度自适应算法

```kotlin
// 答对了 → 提高难度
if (isCorrect) {
    difficulty = minOf(5, currentDifficulty + 1)
} 
// 答错了 → 降低难度
else {
    difficulty = maxOf(1, currentDifficulty - 1)
}
```

### 优先级评分系统

每个单词根据以下因素计算练习优先级：
- **掌握程度**（权重 50%）：越低越优先
- **错误率**（权重 30%）：越高越优先
- **复习间隔**（权重 20%）：超过 3 天加分
- **学习次数**（额外 +20）：少于 3 次加分

---

## 📖 词库说明

### 包含内容
- **上册**：Unit 1-6（60 词）
  - My Classroom
  - My Schoolbag
  - My Friends
  - My Home
  - Dinner's Ready
  - Meet My Family

- **下册**：Unit 1-4（40 词）
  - My School
  - What Time Is It?
  - Weather
  - At the Farm

### 扩展词库
在 `WordDatabase.kt` 中添加更多单词：

```kotlin
Word(
    id = 101,
    word = "apple",
    phonetic = "/ˈæpl/",
    meaning = "苹果",
    example = "I like apples.",
    exampleMeaning = "我喜欢苹果。",
    unit = 11,
    difficulty = 1
)
```

---

## 🎨 界面预览

### 主界面
- 欢迎语 + 学习统计
- 4 个功能卡片（学习、AI 练习、进度、设置）
- 卡通风格，色彩鲜艳

### AI 练习界面
- 进度条 + 题目编号
- 难度星级显示
- 题目卡片（支持播放发音）
- 选项按钮
- 答题反馈（正确答案 + 解析）

---

## 📱 权限说明

| 权限 | 用途 | 必需 |
|------|------|------|
| INTERNET | 可选：在线词库更新 | 否 |
| ACCESS_NETWORK_STATE | 网络状态检测 | 否 |

**注意**：应用核心功能完全离线可用，无需网络权限。

---

## 🔧 自定义配置

### 修改题目数量
```kotlin
// AIPracticeActivity.kt
private val totalQuestions = 10  // 改为任意数字
```

### 修改难度范围
```kotlin
// AIQuestionGenerator.kt
private val difficultyConfig = mapOf(
    1 to 4,   // 难度 1: 4 个选项
    2 to 4,
    3 to 5,   // 难度 3: 5 个选项
    4 to 5,
    5 to 6    // 难度 5: 6 个选项
)
```

### 修改掌握标准
```kotlin
// Word.kt
fun isMastered(): Boolean {
    return masteryLevel >= 80 && timesLearned >= 3
    // 修改 80（正确率）和 3（学习次数）
}
```

---

## 📝 待开发功能

- [ ] 错题本界面
- [ ] 成就徽章系统
- [ ] 学习报告导出
- [ ] 家长控制模式
- [ ] 多人 PK 对战
- [ ] 在线词库同步
- [ ] 语音识别跟读
- [ ] 单词卡片导出

---

## 📄 许可证

MIT License - 可自由使用、修改、分发

---

## 👨‍💻 开发者

为小学四年级学生设计，让英语学习变得快乐有趣！🎉

**版本**: 1.0.0  
**更新日期**: 2025 年
