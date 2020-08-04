import kotlin.math.max

/**
 * author: tang
 * created on: 2019-08-23 15:36
 * description:
 */

//最长回文子串
//
//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
//
//示例 1：
//
//输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
//示例 2：
//
//输入: "cbbd"
//输出: "bb"

// 我们知道回文串一定是对称的，所以我们可以每次循环选择一个中心，
// 进行左右扩展，判断左右字符是否相等即可。
//
//
//
// 由于存在奇数的字符串和偶数的字符串，
// 所以我们需要从一个字符开始扩展，或者从两个字符之间开始扩展，所以总共有 n+n-1 个中心。
// 中心扩展法中的2n-1个中心，可以这么理解：n个数，n-1个间隔，所以就有n+n-1个中心。

//复杂度分析
//
//时间复杂度：O(n^2)，由于围绕中心来扩展回文会耗去 O(n)的时间，所以总的复杂度为 O(n^2)
//
//空间复杂度：O(1)O(1)。

fun longestPalindrome(s: String?): String {
  if (s == null || s.isEmpty()) return ""
  var start = 0
  var end = 0
  for (middle in 0 until s.length) {
    val len1 = expandAroundCenter(s = s, left = middle, right = middle);
    val len2 = expandAroundCenter(s = s, left = middle, right = middle + 1);
    val len = max(len1, len2);
    if (len > end - start) {
      start = middle - (len - 1) / 2;
      end = middle + len / 2;
    }
  }
  return s.substring(start, end + 1);
}

fun expandAroundCenter(s: String, left: Int, right: Int): Int {
  var L = left
  var R = right
  while (L >= 0 && R < s.length && s[L] == s[R]) {
    L--
    R++
  }
  return R - L - 1;
}

// P(i,j) = true 那么s[i,j]是回文串
// 有结论: P(i,j)=(P(i+1,j−1)&&S[i]==S[j])
// 如果我们想知道 P（i,j）的情况，不需要调用判断回文串的函数了，
// 只需要知道 P（i + 1，j - 1）的情况就可以了，这样时间复杂度就少了 O(n)O(n)。
// 因此我们可以用动态规划的方法，空间换时间，把已经求出的 P（i，j）P（i，j）存储起来
// 动态规划
fun longestPalindrome2(s: String): String {
  val length = s.length
  //二位布尔数组
  val P = Array(length) { BooleanArray(length) }
  val maxLen = 0
  var maxPal = ""
  for (len in 1..length)
  //遍历所有的长度
    for (start in 0 until length) {
      val end = start + len - 1
      if (end >= length)
      //下标已经越界，结束本次循环
        break
      P[start][end] =
          (len == 1 || len == 2 || P[start + 1][end - 1]) && s[start] == s[end] //长度为 1 和 2 的单独判断下
      if (P[start][end] && len > maxLen) {
        maxPal = s.substring(start, end + 1)
      }
    }
  return maxPal
}

fun main() {
  val longestPalindrome = longestPalindrome("abc")
  println(longestPalindrome)

  val longestPalindrome2 = longestPalindrome2("abc")
  println(longestPalindrome2)
}