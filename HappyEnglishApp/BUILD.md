# 🚀 快速构建指南

## 方式一：Android Studio（推荐）

### 1. 打开项目
1. 启动 Android Studio
2. File → Open
3. 选择 `HappyEnglishApp` 文件夹
4. 点击 "OK"

### 2. 等待同步
- Gradle 会自动下载依赖
- 首次同步可能需要 5-10 分钟
- 确保网络连接正常

### 3. 运行应用
1. 连接 Android 设备 **或** 启动模拟器
2. 点击顶部绿色运行按钮（▶️）
3. 选择目标设备
4. 等待安装完成

### 4. 打包 APK
```bash
# Debug 版本
./gradlew assembleDebug

# Release 版本（需要签名配置）
./gradlew assembleRelease
```

APK 位置：
- Debug: `app/build/outputs/apk/debug/app-debug.apk`
- Release: `app/build/outputs/apk/release/app-release.apk`

---

## 方式二：命令行构建

### 前置条件
```bash
# 安装 JDK 17
java -version

# 设置 ANDROID_HOME
export ANDROID_HOME=/path/to/android/sdk
```

### 构建步骤
```bash
cd HappyEnglishApp

# 授予执行权限
chmod +x gradlew

# 构建 Debug 版
./gradlew assembleDebug

# 构建 Release 版
./gradlew assembleRelease
```

---

## 模拟器推荐配置

| 设置 | 推荐值 |
|------|--------|
| Android 版本 | 10.0 (API 29) 或更高 |
| RAM | 2048 MB 以上 |
| 存储 | 4096 MB 以上 |
| 屏幕分辨率 | 1080 x 1920 |

---

## 常见问题

### Q: Gradle 同步失败
**A:** 
1. 检查网络连接
2. File → Invalidate Caches → Restart
3. 删除 `.gradle` 文件夹重新同步

### Q: 找不到 SDK
**A:**
1. File → Project Structure
2. SDK Location → 选择正确的 Android SDK 路径

### Q: 构建速度慢
**A:**
在 `gradle.properties` 中添加：
```properties
org.gradle.daemon=true
org.gradle.parallel=true
org.gradle.caching=true
```

### Q: 无法安装到设备
**A:**
1. 开启开发者选项
2. 开启 USB 调试
3. 允许 USB 调试授权

---

## 真机测试

### 1. 开启开发者选项
- 设置 → 关于手机 → 连续点击"版本号"7 次

### 2. 开启 USB 调试
- 设置 → 开发者选项 → USB 调试（开启）

### 3. 连接电脑
- 用 USB 线连接手机
- 手机上允许 USB 调试授权

### 4. 运行
- Android Studio 会识别设备
- 点击运行即可安装

---

## 安装 APK 到手机

### 方式一：直接传输
1. 将 APK 文件传到手机
2. 在手机上点击安装
3. 允许"未知来源"安装

### 方式二：ADB 安装
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### 方式三：二维码下载
1. 将 APK 上传到服务器或云盘
2. 生成下载二维码
3. 手机扫码下载安装

---

## 下一步

构建成功后：
1. ✅ 测试单词学习功能
2. ✅ 测试 AI 智能出题
3. ✅ 测试答题反馈
4. ✅ 检查 TTS 发音
5. 📝 添加更多单词
6. 🎨 优化界面设计

祝开发顺利！🎉
