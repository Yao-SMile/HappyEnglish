package com.happyenglish.app.data

import com.happyenglish.app.data.model.Word

/**
 * 小学四年级英语词库（人教版）
 * 包含上下册约 300 个核心单词
 */
object WordDatabase {
    
    val allWords = listOf(
        // ========== Unit 1: My Classroom ==========
        Word(1, "classroom", "/ˈklɑːsruːm/", "教室", "This is our classroom.", "这是我们的教室。", 1),
        Word(2, "window", "/ˈwɪndəʊ/", "窗户", "Open the window, please.", "请打开窗户。", 1),
        Word(3, "door", "/dɔː/", "门", "Close the door.", "关上门。", 1),
        Word(4, "picture", "/ˈpɪktʃə/", "图画", "There is a picture on the wall.", "墙上有一幅画。", 1),
        Word(5, "floor", "/flɔː/", "地板", "The floor is clean.", "地板很干净。", 1),
        Word(6, "teacher", "/ˈtiːtʃə/", "老师", "Our teacher is kind.", "我们的老师很和蔼。", 1),
        Word(7, "student", "/ˈstjuːdnt/", "学生", "I am a student.", "我是一名学生。", 1),
        Word(8, "desk", "/desk/", "书桌", "My desk is new.", "我的书桌是新的。", 1),
        Word(9, "chair", "/tʃeə/", "椅子", "Sit on the chair.", "坐在椅子上。", 1),
        Word(10, "light", "/laɪt/", "灯", "Turn on the light.", "打开灯。", 1),
        
        // ========== Unit 2: My Schoolbag ==========
        Word(11, "schoolbag", "/ˈskuːlbæɡ/", "书包", "My schoolbag is heavy.", "我的书包很重。", 2),
        Word(12, "maths", "/mæθs/", "数学", "I like maths.", "我喜欢数学。", 2),
        Word(13, "English", "/ˈɪŋɡlɪʃ/", "英语", "English is fun.", "英语很有趣。", 2),
        Word(14, "Chinese", "/ˌtʃaɪˈniːz/", "语文", "Chinese is my favorite.", "语文是我的最爱。", 2),
        Word(15, "storybook", "/ˈstɔːribʊk/", "故事书", "This is a storybook.", "这是一本故事书。", 2),
        Word(16, "notebook", "/ˈnəʊtbʊk/", "笔记本", "Write in your notebook.", "写在你的笔记本上。", 2),
        Word(17, "candy", "/ˈkændi/", "糖果", "Do you like candy?", "你喜欢糖果吗？", 2),
        Word(18, "key", "/kiː/", "钥匙", "Where is my key?", "我的钥匙在哪里？", 2),
        Word(19, "toy", "/tɔɪ/", "玩具", "This is my toy.", "这是我的玩具。", 2),
        Word(20, "pen", "/pen/", "钢笔", "I have a pen.", "我有一支钢笔。", 2),
        
        // ========== Unit 3: My Friends ==========
        Word(21, "strong", "/strɒŋ/", "强壮的", "He is strong.", "他很强壮。", 3),
        Word(22, "friendly", "/ˈfrendli/", "友好的", "She is friendly.", "她很友好。", 3),
        Word(23, "quiet", "/ˈkwaɪət/", "安静的", "Be quiet, please.", "请安静。", 3),
        Word(24, "cute", "/kjuːt/", "可爱的", "The cat is cute.", "这只猫很可爱。", 3),
        Word(25, "tall", "/tɔːl/", "高的", "He is tall.", "他很高。", 3),
        Word(26, "short", "/ʃɔːt/", "矮的", "She is short.", "她很矮。", 3),
        Word(27, "thin", "/θɪn/", "瘦的", "He is thin.", "他很瘦。", 3),
        Word(28, "fat", "/fæt/", "胖的", "The panda is fat.", "这只熊猫很胖。", 3),
        Word(29, "boy", "/bɔɪ/", "男孩", "He is a boy.", "他是一个男孩。", 3),
        Word(30, "girl", "/ɡɜːl/", "女孩", "She is a girl.", "她是一个女孩。", 3),
        
        // ========== Unit 4: My Home ==========
        Word(31, "bedroom", "/ˈbedruːm/", "卧室", "This is my bedroom.", "这是我的卧室。", 4),
        Word(32, "living room", "/ˈlɪvɪŋ ruːm/", "客厅", "We watch TV in the living room.", "我们在客厅看电视。", 4),
        Word(33, "kitchen", "/ˈkɪtʃɪn/", "厨房", "Mom is in the kitchen.", "妈妈在厨房。", 4),
        Word(34, "bathroom", "/ˈbɑːθruːm/", "浴室", "The bathroom is clean.", "浴室很干净。", 4),
        Word(35, "bed", "/bed/", "床", "Go to bed.", "去睡觉。", 4),
        Word(36, "sofa", "/ˈsəʊfə/", "沙发", "Sit on the sofa.", "坐在沙发上。", 4),
        Word(37, "phone", "/fəʊn/", "电话", "The phone is ringing.", "电话在响。", 4),
        Word(38, "table", "/ˈteɪbl/", "桌子", "The table is big.", "桌子很大。", 4),
        Word(39, "fridge", "/frɪdʒ/", "冰箱", "Open the fridge.", "打开冰箱。", 4),
        Word(40, "home", "/həʊm/", "家", "Welcome to my home.", "欢迎来我家。", 4),
        
        // ========== Unit 5: Dinner's Ready ==========
        Word(41, "beef", "/biːf/", "牛肉", "I like beef.", "我喜欢牛肉。", 5),
        Word(42, "chicken", "/ˈtʃɪkɪn/", "鸡肉", "Chicken is delicious.", "鸡肉很美味。", 5),
        Word(43, "noodles", "/ˈnuːdlz/", "面条", "I want noodles.", "我想要面条。", 5),
        Word(44, "soup", "/suːp/", "汤", "The soup is hot.", "汤很热。", 5),
        Word(45, "vegetable", "/ˈvedʒtəbl/", "蔬菜", "Eat your vegetables.", "吃你的蔬菜。", 5),
        Word(46, "chopsticks", "/ˈtʃɒpstɪks/", "筷子", "Use chopsticks.", "用筷子。", 5),
        Word(47, "bowl", "/bəʊl/", "碗", "Pass me the bowl.", "把碗递给我。", 5),
        Word(48, "fork", "/fɔːk/", "叉子", "Use a fork.", "用叉子。", 5),
        Word(49, "knife", "/naɪf/", "刀", "Be careful with the knife.", "小心用刀。", 5),
        Word(50, "spoon", "/spuːn/", "勺子", "Use a spoon.", "用勺子。", 5),
        
        // ========== Unit 6: Meet My Family ==========
        Word(51, "family", "/ˈfæməli/", "家庭", "I love my family.", "我爱我的家庭。", 6),
        Word(52, "parents", "/ˈpeərənts/", "父母", "My parents are kind.", "我的父母很和蔼。", 6),
        Word(53, "father", "/ˈfɑːðə/", "父亲", "My father is a doctor.", "我的父亲是医生。", 6),
        Word(54, "mother", "/ˈmʌðə/", "母亲", "My mother is a teacher.", "我的母亲是老师。", 6),
        Word(55, "uncle", "/ˈʌŋkl/", "叔叔", "My uncle is tall.", "我的叔叔很高。", 6),
        Word(56, "aunt", "/ɑːnt/", "阿姨", "My aunt is nice.", "我的阿姨很好。", 6),
        Word(57, "cousin", "/ˈkʌzn/", "表兄弟姐妹", "My cousin is my age.", "我的表亲和我同岁。", 6),
        Word(58, "baby", "/ˈbeɪbi/", "婴儿", "The baby is cute.", "婴儿很可爱。", 6),
        Word(59, "brother", "/ˈbrʌðə/", "兄弟", "I have a brother.", "我有一个兄弟。", 6),
        Word(60, "sister", "/ˈsɪstə/", "姐妹", "She is my sister.", "她是我的姐妹。", 6),
        
        // ========== 下册 Unit 1: My School ==========
        Word(61, "school", "/skuːl/", "学校", "I go to school every day.", "我每天去学校。", 7),
        Word(62, "library", "/ˈlaɪbrəri/", "图书馆", "Read books in the library.", "在图书馆看书。", 7),
        Word(63, "playground", "/ˈpleɪɡraʊnd/", "操场", "We play on the playground.", "我们在操场玩。", 7),
        Word(64, "garden", "/ˈɡɑːdn/", "花园", "The garden is beautiful.", "花园很美。", 7),
        Word(65, "art room", "/ɑːt ruːm/", "美术室", "We draw in the art room.", "我们在美术室画画。", 7),
        Word(66, "music room", "/ˈmjuːzɪk ruːm/", "音乐室", "Sing in the music room.", "在音乐室唱歌。", 7),
        Word(67, "computer", "/kəmˈpjuːtə/", "电脑", "I use the computer.", "我用电脑。", 7),
        Word(68, "room", "/ruːm/", "房间", "This room is big.", "这个房间很大。", 7),
        Word(69, "first", "/fɜːst/", "第一", "I am first.", "我是第一。", 7),
        Word(70, "second", "/ˈsekənd/", "第二", "She is second.", "她是第二。", 7),
        
        // ========== 下册 Unit 2: What Time Is It? ==========
        Word(71, "time", "/taɪm/", "时间", "What time is it?", "现在几点了？", 8),
        Word(72, "clock", "/klɒk/", "钟", "Look at the clock.", "看钟。", 8),
        Word(73, "o'clock", "/əˈklɒk/", "点钟", "It's three o'clock.", "现在三点钟。", 8),
        Word(74, "breakfast", "/ˈbrekfəst/", "早餐", "I eat breakfast at 7.", "我 7 点吃早餐。", 8),
        Word(75, "lunch", "/lʌntʃ/", "午餐", "Let's have lunch.", "我们吃午餐吧。", 8),
        Word(76, "dinner", "/ˈdɪnə/", "晚餐", "Dinner is ready.", "晚餐准备好了。", 8),
        Word(77, "get up", "/ɡet ʌp/", "起床", "I get up at 6.", "我 6 点起床。", 8),
        Word(78, "go to bed", "/ɡəʊ tuː bed/", "睡觉", "Go to bed now.", "现在去睡觉。", 8),
        Word(79, "go home", "/ɡəʊ həʊm/", "回家", "Let's go home.", "我们回家吧。", 8),
        Word(80, "go to school", "/ɡəʊ tuː skuːl/", "去上学", "I go to school by bus.", "我乘公交去上学。", 8),
        
        // ========== 下册 Unit 3: Weather ==========
        Word(81, "weather", "/ˈweðə/", "天气", "How is the weather?", "天气怎么样？", 9),
        Word(82, "sunny", "/ˈsʌni/", "晴朗的", "It's sunny today.", "今天晴朗。", 9),
        Word(83, "cloudy", "/ˈklaʊdi/", "多云的", "It's cloudy now.", "现在多云。", 9),
        Word(84, "rainy", "/ˈreɪni/", "下雨的", "It's rainy outside.", "外面在下雨。", 9),
        Word(85, "windy", "/ˈwɪndi/", "刮风的", "It's windy today.", "今天刮风。", 9),
        Word(86, "snowy", "/ˈsnəʊi/", "下雪的", "It's snowy in winter.", "冬天下雪。", 9),
        Word(87, "hot", "/hɒt/", "热的", "It's hot in summer.", "夏天很热。", 9),
        Word(88, "cold", "/kəʊld/", "冷的", "It's cold today.", "今天很冷。", 9),
        Word(89, "warm", "/wɔːm/", "温暖的", "It's warm in spring.", "春天很温暖。", 9),
        Word(90, "cool", "/kuːl/", "凉爽的", "It's cool in autumn.", "秋天很凉爽。", 9),
        
        // ========== 下册 Unit 4: At the Farm ==========
        Word(91, "farm", "/fɑːm/", "农场", "We visit a farm.", "我们参观农场。", 10),
        Word(92, "horse", "/hɔːs/", "马", "The horse is fast.", "马很快。", 10),
        Word(93, "cow", "/kaʊ/", "奶牛", "The cow gives milk.", "奶牛产奶。", 10),
        Word(94, "sheep", "/ʃiːp/", "绵羊", "The sheep is white.", "绵羊是白色的。", 10),
        Word(95, "hen", "/hen/", "母鸡", "The hen lays eggs.", "母鸡下蛋。", 10),
        Word(96, "duck", "/dʌk/", "鸭子", "The duck swims.", "鸭子游泳。", 10),
        Word(97, "pig", "/pɪɡ/", "猪", "The pig is fat.", "猪很胖。", 10),
        Word(98, "goat", "/ɡəʊt/", "山羊", "The goat eats grass.", "山羊吃草。", 10),
        Word(99, "tomato", "/təˈmɑːtəʊ/", "西红柿", "I like tomatoes.", "我喜欢西红柿。", 10),
        Word(100, "potato", "/pəˈteɪtəʊ/", "土豆", "Potatoes are yummy.", "土豆很好吃。", 10)
    )
    
    /**
     * 根据单元获取单词
     */
    fun getWordsByUnit(unit: Int): List<Word> {
        return allWords.filter { it.unit == unit }
    }
    
    /**
     * 获取所有单元列表
     */
    fun getAllUnits(): List<Int> {
        return allWords.map { it.unit }.distinct().sorted()
    }
    
    /**
     * 根据 ID 获取单词
     */
    fun getWordById(id: Int): Word? {
        return allWords.find { it.id == id }
    }
    
    /**
     * 获取随机单词（用于练习）
     */
    fun getRandomWords(count: Int, excludeIds: Set<Int> = emptySet()): List<Word> {
        val available = allWords.filter { it.id !in excludeIds }
        return available.shuffled().take(count)
    }
    
    /**
     * 获取未掌握的单词
     */
    fun getWordsToReview(progressMap: Map<Int, com.happyenglish.app.data.model.LearningProgress>): List<Word> {
        return allWords.filter { word ->
            val progress = progressMap[word.id]
            progress == null || !progress.isMastered()
        }
    }
    
    /**
     * 总单词数
     */
    fun getTotalCount(): Int = allWords.size
}
