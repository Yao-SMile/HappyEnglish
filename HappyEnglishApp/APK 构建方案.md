# 📱 快乐学英语 APK 构建方案

由于当前环境没有 Android SDK，我为你提供**3 种获取 APK 的方案**：

---

## ✅ 方案一：使用 Android Studio（最简单，推荐）

### 步骤

**1. 下载 Android Studio**
```
官网：https://developer.android.com/studio
选择：Windows/Mac/Linux 对应版本
大小：约 1 GB
```

**2. 安装并打开项目**
```
1. 安装 Android Studio
2. 启动后选择 "Open"
3. 选择桌面的 HappyEnglishApp 文件夹
4. 点击 "OK"
```

**3. 等待 Gradle 同步**
```
- 首次同步需要 5-15 分钟
- 会自动下载 Android SDK 和依赖
- 确保网络连接正常
```

**4. 构建 APK**
```
方法 A（图形界面）：
Build → Build Bundle(s) / APK(s) → Build APK(s)

方法 B（快捷键）：
Ctrl + Shift + A → 输入 "Build APK" → 回车
```

**5. 获取 APK**
```
位置：HappyEnglishApp/app/build/outputs/apk/debug/app-debug.apk
大小：约 15-25 MB
```

**6. 安装到手机**
```
方法 1：USB 传输
- 用数据线连接手机
- 复制 APK 到手机
- 在手机上点击安装

方法 2：ADB 安装
adb install app/build/outputs/apk/debug/app-debug.apk

方法 3：二维码下载
- 将 APK 上传到网盘
- 生成下载二维码
- 手机扫码下载
```

---

## ✅ 方案二：使用在线构建服务（无需安装）

### 选项 A：GitHub Actions（免费）

**1. 创建 GitHub 仓库**
```
1. 访问 https://github.com
2. 注册/登录账号
3. 点击 "+" → "New repository"
4. 名称：HappyEnglish
5. 选择 "Public"
6. 点击 "Create repository"
```

**2. 上传项目代码**
```bash
# 在项目目录执行
cd ~/Desktop/HappyEnglishApp
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/你的用户名/HappyEnglish.git
git push -u origin main
```

**3. 添加构建配置文件**

创建 `.github/workflows/build.yml`：

```yaml
name: Android Build

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]
  workflow_dispatch:  # 允许手动触发

jobs:
  build:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v4
    
    - name: Set up JDK 17
      uses: actions/setup-java@v4
      with:
        java-version: '17'
        distribution: 'temurin'
    
    - name: Setup Gradle
      uses: gradle/gradle-build-action@v3
    
    - name: Build Debug APK
      run: ./gradlew assembleDebug
    
    - name: Upload APK
      uses: actions/upload-artifact@v4
      with:
        name: app-debug
        path: app/build/outputs/apk/debug/app-debug.apk
```

**4. 触发构建**
```
1. 推送代码后自动构建
2. 或手动触发：Actions → Build → Run workflow
3. 构建完成后下载 APK
```

**5. 下载 APK**
```
1. 进入 Actions 标签
2. 点击最近的构建
3. 在 "Artifacts" 部分下载 app-debug.zip
4. 解压得到 APK
```

### 选项 B：Codemagic（免费，专为移动应用）

```
官网：https://codemagic.io
1. 注册账号
2. 连接 GitHub 仓库
3. 自动检测 Android 项目
4. 点击 "Start build"
5. 构建完成后下载 APK
```

### 选项 C：Bitrise（免费额度）

```
官网：https://www.bitrise.io
1. 注册账号
2. 添加项目
3. 选择 Android 工作流
4. 开始构建
```

---

## ✅ 方案三：使用命令行（需要 Android SDK）

### 前置条件

**1. 安装 JDK 17**
```bash
# Ubuntu/Debian
sudo apt install openjdk-17-jdk

# macOS
brew install openjdk@17

# Windows
下载：https://adoptium.net/
```

**2. 安装 Android SDK**
```bash
# 下载命令行工具
https://developer.android.com/studio#command-tools

# 解压后设置环境变量
export ANDROID_HOME=$HOME/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools

# 安装必要的 SDK 组件
sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"
```

**3. 下载 Gradle Wrapper**
```bash
cd HappyEnglishApp

# 下载 gradle-wrapper.jar
mkdir -p gradle/wrapper
curl -L https://raw.githubusercontent.com/gradle/gradle/master/gradle/wrapper/gradle-wrapper.jar \
  -o gradle/wrapper/gradle-wrapper.jar
```

**4. 构建 APK**
```bash
chmod +x gradlew
./gradlew assembleDebug

# APK 位置
app/build/outputs/apk/debug/app-debug.apk
```

---

## 📥 快速下载（推荐方式）

### 如果你已经有 Android Studio

```
1. 打开 Android Studio
2. File → Open → 选择 HappyEnglishApp
3. 等待同步完成
4. Build → Build APK
5. 等待构建完成（约 2-5 分钟）
6. 点击 "locate" 找到 APK
7. 复制到手机安装
```

### 如果你想用在线服务

```
推荐使用 GitHub Actions：
1. 创建 GitHub 账号
2. 上传项目代码
3. 添加 .github/workflows/build.yml
4. 推送代码自动构建
5. 下载生成的 APK

优点：
✅ 免费
✅ 无需本地环境
✅ 每次提交自动构建
✅ 可分享构建历史
```

---

## 🔧 故障排除

### 问题 1：Gradle 同步失败
```
解决：
1. File → Invalidate Caches → Restart
2. 删除 .gradle 文件夹
3. 重新同步
4. 检查网络连接
```

### 问题 2：SDK 未找到
```
解决：
1. File → Project Structure
2. SDK Location → 选择正确的 Android SDK 路径
3. 或让 Android Studio 自动下载
```

### 问题 3：构建超时
```
解决：
在 gradle.properties 添加：
org.gradle.daemon=true
org.gradle.parallel=true
org.gradle.caching=true
org.gradle.jvmargs=-Xmx4096m
```

### 问题 4：签名错误
```
解决：
Debug 版本不需要签名配置
如需 Release 版本，需要创建签名文件
```

---

## 📊 构建时间参考

| 方式 | 首次构建 | 后续构建 |
|------|----------|----------|
| Android Studio | 10-20 分钟 | 2-5 分钟 |
| GitHub Actions | 15-25 分钟 | 10-15 分钟 |
| 命令行（有 SDK） | 5-10 分钟 | 2-3 分钟 |

---

## 📱 APK 安装说明

### 安装到 Android 手机

**方法 1：直接安装**
```
1. 将 APK 传到手机（微信/QQ/数据线）
2. 在手机上点击 APK 文件
3. 允许"未知来源"安装
4. 等待安装完成
```

**方法 2：ADB 安装**
```bash
# 手机开启 USB 调试
# 连接电脑
adb devices  # 确认设备连接
adb install app-debug.apk
```

**方法 3：网盘下载**
```
1. 上传 APK 到网盘（百度网盘/阿里云盘等）
2. 生成分享链接
3. 手机打开链接下载
4. 安装 APK
```

---

## 🎯 推荐方案

| 你的情况 | 推荐方案 |
|----------|----------|
| 有电脑，想长期开发 | Android Studio |
| 只想快速获得 APK | GitHub Actions |
| 没有电脑，只有手机 | 找朋友帮忙构建 |
| 想学习 Android 开发 | Android Studio + 命令行 |

---

## 📞 需要帮助？

如果你在选择方案或构建过程中遇到问题，请告诉我：
- 你的操作系统（Windows/Mac/Linux）
- 是否有 Android Studio
- 是否有 GitHub 账号
- 具体遇到的错误信息

我会提供针对性的帮助！

---

**祝你顺利获得 APK！🎉**
