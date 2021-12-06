import java.util.HashMap

//
// 示例 2：
//
//
//输入：s = "a", t = "a"
//输出："a"
//
//
// 示例 3:
//
//
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。
//
// 输入：s = "ADOBECODEBANC", t = "ABC"
// 输出："BANC"
//
//
// 提示：
//
//
// 1 <= s.length, t.length <= 105
// s 和 t 由英文字母组成
//
//
//
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口
// 👍 1245 👎 0
internal class Solution76 {

  //HashMap(需要的字符,该字符需要的数量)
  private var needMap: HashMap<Char, Int> = HashMap()

  // key是字符,value是该字符在窗口中出现了多少次
  private var windowMap: HashMap<Char, Int> = HashMap()

  fun minWindow(inputString: String, need: String): String {

    for (element in need) {
      needMap[element] = needMap.getOrDefault(element, 0) + 1
    }
    println("needCount: $needMap")

    var windowLeft = 0
    var windowRight = 0
    var valid = 0

    var minLen = Int.MAX_VALUE
    // 最小子串的开始索引位置
    var start = 0

    while (windowRight < inputString.length) {
      // [0 ~ inputStringLen - 1] 即将进入窗口的c1
      val c1 = inputString[windowRight]
      windowRight++
      if (needMap.containsKey(c1)) {
        //按照need中的字符串，维护一个window,key是字符串,value是该字符串在窗口中出现了多少次
        windowMap[c1] = windowMap.getOrDefault(c1, 0) + 1
        if (windowMap[c1] == needMap[c1])
          valid++
      }
      println("valid: $valid")

      // need to 开始收缩?
      while (valid == needMap.size) {
        //已经全部找到need字符串，开始从左侧缩小window
        println("开始左移窗口..")
        if (windowRight - windowLeft < minLen) {
          start = windowLeft
          minLen = windowRight - windowLeft
          println("current min len = $minLen")
        }
        //d移出窗口
        val d = inputString[windowLeft]
        windowLeft++
        //更新窗口数据
        if (needMap.containsKey(d)) {
          if (windowMap[d] == needMap[d]) {
            valid--
          }
          // 发现了needMap需要的c2，windowCount更新，否则不要更新
          windowMap[d] =
            windowMap.getOrDefault(d, 0) - 1
          println("need to update windows $windowMap")
        }
        println("windowString: ${inputString.substring(start, start + minLen)}")
      }
    }
    return if (minLen == Int.MAX_VALUE) "" else inputString.substring(start, start + minLen)
  }
}

fun main() {
  val need = "ABC"
  val string = "ADOBECODEBANC"
  val result = Solution76().minWindow(string, need)
  println(result)
}