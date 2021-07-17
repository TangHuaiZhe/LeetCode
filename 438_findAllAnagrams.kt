import java.util.HashMap

//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
//
// 异位词 指字母相同，但排列不同的字符串。
//
//
//
// 示例 1:
//
//
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
//
//
// 示例 2:
//
//
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
//
//
//
//
// 提示:
//
//
// 1 <= s.length, p.length <= 3 * 104
// s 和 p 仅包含小写字母
//
// Related Topics 哈希表 字符串 滑动窗口
// 👍 564 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class Solution438 {

  //(需要的字符串,该字符串需要的数量)
  private var needMap: HashMap<Char, Int> = HashMap()

  // key是字符串,value是该字符串在窗口中出现了多少次
  private var windowMap: HashMap<Char, Int> = HashMap()

  fun findAnagrams(inputString: String, need: String): List<Int> {
    for (element in need) {
      needMap[element] = needMap.getOrDefault(element, 0) + 1
    }
    println("needCount: $needMap")

    var windowStart = 0
    var windowEnd = 0
    var valid = 0

    // 子串的开始索引位置
    var start = 0
    var len = Int.MAX_VALUE
    val result = ArrayList<Int>()


    while (windowEnd < inputString.length) {
      // [0 ~ inputStringLen - 1] 即将进入窗口的c1
      val c1 = inputString[windowEnd]
      windowEnd++
      println("windowRight:$windowEnd")
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
      while (windowEnd - windowStart >= need.length) {
        if (valid == needMap.size) {
          result.add(windowStart)
        }
        //已经全部找到need字符串，开始从左侧缩小window
        println("开始左移窗口..")
        println("window len: ${windowEnd - windowStart}")
        println("len: $len")
        if (windowEnd - windowStart < len) {
          start = windowStart
          len = windowEnd - windowStart
          println("start: $start")
        }
        println("windowString: ${inputString.substring(start, start + len)}")
        //d移出窗口
        val d = inputString[windowStart]
        windowStart++
        //更新窗口数据
        if (needMap.containsKey(d)) {
          if (windowMap[d] == needMap[d]) {
            valid--
          }
          // 发现了需要的c2，windowCount更新，否则不要更新，因为存储的时候
          windowMap[d] =
            windowMap.getOrDefault(d, 0) - 1
        }
      }
    }
    return result
  }
}
//leetcode submit region end(Prohibit modification and deletion)

fun main() {
  val need = "abc"
  val string = "cbaebabacd"
  val result = Solution438().findAnagrams(string, need)
  println(result)
}