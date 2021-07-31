package jzoffer

import kotlin.math.max
import kotlin.math.pow

/**
 *
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 *
 * 动态规划
 * 有贪心解法
 *
 */

internal class Solution14 {
  fun cuttingRope(n: Int): Int {
    if (n == 0) return 0
    if (n == 1) return 0
    if (n == 2) return 1
    if (n == 3) return 2

    // dp[n]：长度为n的绳子，剪成m段之后最大的乘积
    val dp = IntArray(n + 1)
    dp[0] = 0
    dp[1] = 0
    dp[2] = 1
    dp[3] = 2
    for (i in 4..n) {
      //剪1没有意义，对乘积没有增大效果，从2开始剪
      for (j in 2..i) {
        // 剪了第一段后，剩下(i - j)长度可以剪也可以不剪。
        // 如果不剪的话长度乘积即为j * (i - j)；
        // 如果剪的话长度乘积即为j * dp[i - j]。取两者最大值max(j * (i - j), j * dp[i - j])
        dp[i] = max(dp[i], max(dp[i - j] * j, (i - j) * j))
      }
    }
    return dp[n]
  }

  /**
   * 核心思路是：尽可能把绳子分成长度为3的小段，这样乘积最大
   *
   * 数学推导总体分为两步：
   * ① 当所有绳段长度相等时，乘积最大。
   * ② 最优的绳段长度为3
   * https://leetcode-cn.com/problems/jian-sheng-zi-lcof/solution/mian-shi-ti-14-i-jian-sheng-zi-tan-xin-si-xiang-by/
   */
  fun cuttingRope2(n: Int): Int {
    if (n <= 3) return n - 1
    val a = n / 3
    val b = n % 3
    // 把绳子尽可能切为多个长度为3的片段，留下的最后一段绳子的长度可能为0,1,2三种情况
    if (b == 0) return 3.0.pow(a).toInt() //0的情况，直接X
    return if (b == 1) 3.0.pow(a - 1).toInt() * 4 // 1的情况,要将一个3+1 转换为2+2
    else 3.0.pow(a.toDouble()).toInt() * 2 // 2的情况，保留，不再拆分为1+1
  }
}

fun main() {
  // println(Solution14().cuttingRope(0))
  // println(Solution14().cuttingRope(1))
  // println(Solution14().cuttingRope(2))
  // println(Solution14().cuttingRope(3))
  println(Solution14().cuttingRope(4))
  println(Solution14().cuttingRope(10))


  println(Solution14().cuttingRope2(4))
  println(Solution14().cuttingRope2(10))
}