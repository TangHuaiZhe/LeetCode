/**
 * author: tang
 * created on: 2019-11-06 09:59
 * description:
 *
 *

滑动窗口/双指针

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 *
 */
fun lengthOfLongestSubstring(input: String): Int {
  if (input.isEmpty()) {
    return 0
  }
  var a = 0
  var b = 0
  var max = 1
  val tempResult = HashSet<Char>()
  tempResult.add(input[0])
  while (a < input.length - 1 && b < input.length - 1) {
    b++
    if (!tempResult.contains(input[b])) {
      tempResult.add(input[b])
      if (tempResult.size > max) {
        max = tempResult.size
        println("maxLength is ${tempResult.size}")
        println("maxString is $tempResult")
      }
    } else {
      a++
      b = a
      tempResult.clear()
      tempResult.add(input[a])
    }
  }
  return max
}

fun lengthOfLongestSubstring1(s: String): Int {
  val maxCharList = ArrayList<Char>()
  var maxLen = 0
  for (char in s) {
    if (!maxCharList.contains(char)) {
      maxCharList.add(char)
    } else {
      maxCharList.add(char)
      val index = maxCharList.indexOf(char)
      // 等于删除0 到 index的字符 考虑pwwk的情况 滑动窗口不能再简单滑动 需要一次移动到没有重复字符的位置
      for (i in 0..index) {
        maxCharList.removeAt(0)
      }
    }
    if (maxLen < maxCharList.size) {
      maxLen = maxCharList.size
    }
  }
  return maxLen
}

fun main() {
  var input = "abcabcbb"
  input = "pwwkew"
  val lengthOfLongestSubstring = lengthOfLongestSubstring1(input)
  println(lengthOfLongestSubstring)
}