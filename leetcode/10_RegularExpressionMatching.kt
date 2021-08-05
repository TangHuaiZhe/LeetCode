/**
 * author: tang
 * created on: 2019-08-19 10:28
 * description:
 */

/**
 *
 * 给你一个字符串 text 和一个字符规律 pattern，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

 * '.' 匹配任意 单个字符
 * '*' 匹配 零个或多个 前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 * text 可能为空，且只包含从 a-z 的小写字母。
 * pattern 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * 示例 1:
 * 输入:
 * text = "aa"
 * pattern = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入:
 * text = "aa"
 * pattern = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例 3:
 * 输入:
 * text = "ab"
 * pattern = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 示例 4:
 * 输入:
 * text = "aab"
 * pattern = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 * 示例 5:
 * 输入:
 * text = "mississippi"
 * pattern = "mis*is*pattern*."
 * 输出: false
 *
 *
 */

fun isMatch(text: String?, pattern: String?): Boolean {
  if (text == null || pattern == null) {
    return false
  }

  // 二维boolean数组 [text.length() + 1] [pattern.length() + 1]
  val dp = Array(text.length + 1) { BooleanArray(pattern.length + 1) }
  dp[0][0] = true
  for (i in pattern.indices) {
    if (pattern[i] == '*' && dp[0][i - 1]) {
      dp[0][i + 1] = true
    }
  }
  for (i in text.indices) {
    for (j in pattern.indices) {
      if (pattern[j] == '.' || pattern[j] == text[i]) {
        dp[i + 1][j + 1] = dp[i][j]
      }
      if (pattern[j] == '*') {
        if (pattern[j - 1] != text[i] && pattern[j - 1] != '.') {
          //* 匹配了0个字符
          dp[i + 1][j + 1] = dp[i + 1][j - 1]
        } else {
          //* 匹配了s的第i个字符
          dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]
        }
      }
    }
  }
  // printArray(dp);
  return dp[text.length][pattern.length]
}

fun isMatch2(text: String, pattern: String): Boolean {

  println("比较 text = $text and pattern = $pattern")

  if (pattern.isEmpty()) {
    println("pattern.isEmpty and text.isEmpty = ${text.isEmpty()}")
    return text.isEmpty()
  }
  // 第一个字符能对上
  val firstMatch: Boolean =
      text.isNotEmpty() && pattern[0] in arrayListOf(text[0], '.') // 处理「.」通配符
  println("firstMatch = $firstMatch")
  // 处理「*」通配符
  return if (pattern.length >= 2 && pattern[1] == '*') {
    val result =
        isMatch2(text, pattern.substring(2)) || (firstMatch && isMatch2(text.substring(1), pattern))
    println("处理*,匹配结果 $result")
    result
  } else {
    val result = firstMatch && isMatch2(text.substring(1), pattern.substring(1))
    println("无需处理*,匹配结果 $result")
    result
  }
}

fun main() {
  println(isMatch2("aa", "a*"))
  println(isMatch2("aab", "a.b"))
  println(isMatch2("aab", "c*a*b"))
}