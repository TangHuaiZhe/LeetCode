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

  //(需要的字符串,该字符串需要的数量)
  private var needCount: HashMap<Char, Int?> = HashMap()

  // key是字符串,value是该字符串在窗口中出现了多少次
  private var windowCount: HashMap<Char, Int> = HashMap()

  fun minWindow(inputString: String, need: String): String {
    for (element in need) {
      needCount[element] = needCount.getOrDefault(element, 0)!! + 1
    }
    println("needCount: $needCount")
    var windowLeft = 0
    var windowRight = -1 // start from -1
    var len = Int.MAX_VALUE
    var ansL = -1
    var ansR = -1
    val inputStringLen = inputString.length
    while (windowRight < inputStringLen - 1) {
      ++windowRight
      // [0 ~ inputStringLen - 1]
      val c1 = inputString[windowRight]
      if (windowRight < inputStringLen && needCount.containsKey(c1)) {
        //按照need中的字符串，维护一个window,key是字符串,value是该字符串在窗口中出现了多少次
        windowCount[c1] =
          windowCount.getOrDefault(c1, 0) + 1
      }
      println("windowCount +1 : $windowCount")

      while (check() && windowLeft <= windowRight) {
        //已经全部找到need字符串，开始从左侧缩小window
        println("start minor window..")
        if (windowRight - windowLeft + 1 < len) {
          len = windowRight - windowLeft + 1
          ansL = windowLeft
          ansR = windowLeft + len
          println("ansL: $ansL")
          println("ansL: $ansR")
        }
        val c2 = inputString[windowLeft]
        if (needCount.containsKey(c2)) {
          // 发现了需要的c2，windowCount更新，否则不要更新，因为存储的时候
          windowCount[c2] =
            windowCount.getOrDefault(c2, 0) - 1
          println("c2:$c2")
          println("windowCount -1:$windowCount")
        }
        // 不管c2需不需要,都要左移window
        ++windowLeft
      }
    }
    return if (ansL == -1) "" else inputString.substring(ansL, ansR)
  }

  /**
   * 校验字符串是不是全部都找到了
   */
  private fun check(): Boolean {
    for (characterIntegerEntry in needCount.entries) {
      if (windowCount.getOrDefault(characterIntegerEntry.key, 0) < characterIntegerEntry.value!!) {
        // 还有字符串没找到，没放到window中
        return false
      }
    }
    // 已经全部找到
    return true
  }
}

fun main() {
  val need = "ABC"
  val string = "SBJGFHAKNCGTUBYFACSV"
  println(string.length)
  val result = Solution76().minWindow(string, need)
  println(result)
}