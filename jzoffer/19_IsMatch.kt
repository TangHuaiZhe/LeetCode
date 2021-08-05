package jzoffer

/**
请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。

示例 1:
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。

示例 2:
输入:
s = "aa"
p = "a*"
输出: true
解释:因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。

示例3:
输入:
s = "ab"
p = ".*"
输出: true
解释:".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。

示例 4:
输入:
s = "aab"
p = "c*a*b"
输出: true
解释:因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。

示例 5:
输入:
s = "mississippi"
p = "mis*is*p*."
输出: false
s可能为空，且只包含从a-z的小写字母。
p可能为空，且只包含从a-z的小写字母以及字符.和*，无连续的 '*'。

 **/

internal class Solution19 {
  /**
   * A：待匹配字符串
   * B: 模式串
   */
  fun isMatch(A: String, B: String): Boolean {
    val n = A.length
    val m = B.length
    // dp[i][j] = A[0..i] 和B[0..j]是否匹配
    val dp = Array(n + 1) {
      BooleanArray(
        m + 1
      )
    }
    for (i in 0..n) {
      for (j in 0..m) {
        //空正则 B
        if (j == 0) {
          // i==0 A为空串，只要B也为空串，匹配
          dp[i][j] = i == 0
        } else { // 非空正则
          //非空正则分为两种情况 * 和 非*
          if (B[j - 1] != '*') {
            if (i > 0 && (A[i - 1] == B[j - 1] || B[j - 1] == '.')) {
              dp[i][j] = dp[i - 1][j - 1]
            }
          } else {
            //碰到 * 了，分为看和不看两种情况
            //不看，* == 0
            if (j >= 2) {
              dp[i][j] = dp[i][j] or dp[i][j - 2]
            }
            //看，A串往前移动一位
            if (i >= 1 && j >= 2 && (A[i - 1] == B[j - 2] || B[j - 2] == '.')) {
              dp[i][j] = dp[i][j] or dp[i - 1][j]
            }
          }
        }
      }
    }
    return dp[n][m]
  }

  fun isMatch2(s: String, p: String): Boolean {
    val dp = Array(s.length + 1) { BooleanArray(p.length + 1) }
    dp[0][0] = true
    for (i in 1..p.length)
      if (p[i - 1] == '*' && i >= 2)
        dp[0][i] = dp[0][i - 2]
    for (i in 1..s.length) {
      for (j in 1..p.length) {
        if (s[i - 1] == p[j - 1] || p[j - 1] == '.')
          dp[i][j] = dp[i - 1][j - 1]
        else if (p[j - 1] == '*') {
          if (j >= 2) dp[i][j] = dp[i][j - 2]
          if (s[i - 1] == p[j - 2] || p[j - 2] == '.')
            dp[i][j] = dp[i][j] || dp[i - 1][j]
        }
      }
    }
    return dp[s.length][p.length]
  }
}

fun main() {
  println(Solution19().isMatch("aa", "a*"))
  println(Solution19().isMatch("aab", "a.b"))
  println(Solution19().isMatch("aab", "c*a*b"))
}