import java.util.HashMap

//给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
//
// 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
//
//
//
// 示例 1：
//
//
//输入: s1 = "ab" s2 = "eidbaooo"
//输出: True
//解释: s2 包含 s1 的排列之一 ("ba").
//
//
// 示例 2：
//
//
//输入: s1= "ab" s2 = "eidboaoo"
//输出: False
//
//
//
//
// 提示：
//
//
// 1 <= s1.length, s2.length <= 104
// s1 和 s2 仅包含小写字母
//
// Related Topics 哈希表 双指针 字符串 滑动窗口
// 👍 377 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class Solution567 {

  //(需要的字符串,该字符串需要的数量)
  private var needMap: HashMap<Char, Int> = HashMap()

  // key是字符串,value是该字符串在窗口中出现了多少次
  private var windowMap: HashMap<Char, Int> = HashMap()

  // 判断 s 中是否存在 t 的排列
  fun checkInclusion(need: String, inputString: String): Boolean {

    for (element in need) {
      needMap[element] = needMap.getOrDefault(element, 0) + 1
    }
    println("needCount: $needMap")

    var windowLeft = 0
    var windowRight = 0
    var valid = 0

    var len = Int.MAX_VALUE
    // 最小子串的开始索引位置
    var start = 0

    while (windowRight < inputString.length) {
      // [0 ~ inputStringLen - 1] 即将进入窗口的c1
      val c1 = inputString[windowRight]
      windowRight++
      println("windowRight:$windowRight")
      println("c1:$c1")
      if (needMap.containsKey(c1)) {
        //按照need中的字符串，维护一个window,key是字符串,value是该字符串在窗口中出现了多少次
        windowMap[c1] = windowMap.getOrDefault(c1, 0) + 1
        if (windowMap[c1] == needMap[c1])
          valid++
      }
      println("windowCount +1 : $windowMap")
      println("valid: $valid")

      // need to 开始收缩?
      while (windowRight - windowLeft >= need.length) {
        if (valid == needMap.size) return true
        //已经全部找到need字符串，开始从左侧缩小window
        println("开始左移窗口..")
        println("window len: ${windowRight - windowLeft}")
        println("len: $len")
        if (windowRight - windowLeft < len) {
          start = windowLeft
          len = windowRight - windowLeft
          println("start: $start")
        }
        println("windowString: ${inputString.substring(start, start + len)}")
        //d移出窗口
        val d = inputString[windowLeft]
        windowLeft++
        //更新窗口数据
        if (needMap.containsKey(d)) {
          if (windowMap[d] == needMap[d]) {
            valid--
          }
          // 发现了需要的c2，windowCount更新，否则不要更新
          windowMap[d] =
            windowMap.getOrDefault(d, 0) - 1
        }
      }
    }
    return false
  }
}

fun main() {
  val need = "ab"
  val string = "eidbaooo"
  val result = Solution567().checkInclusion(need, string)
  println(result)

  // val result1 = Solution567().checkInclusion(need, "eidboaoo")
  // println(result1)
}
//leetcode submit region end(Prohibit modification and deletion)
