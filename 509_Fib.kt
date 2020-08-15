//斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
//
// F(0) = 0,   F(1) = 1
//F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
//
//
// 给定 N，计算 F(N)。
//
//
//
// 示例 1：
//
// 输入：2
//输出：1
//解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
//
//
// 示例 2：
//
// 输入：3
//输出：2
//解释：F(3) = F(2) + F(1) = 1 + 1 = 2.
//
//
// 示例 3：
//
// 输入：4
//输出：3
//解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
//
//
//
//
// 提示：
//
//
// 0 ≤ N ≤ 30
//
// Related Topics 数组
// 👍 147 👎 0

internal class Solution509 {
  fun fib(N: Int): Int {
    if (N == 0) return 0
    if (N == 1 || N == 2) return 1
    return fib(N - 1) + fib(N - 2)
  }

  /**
   * 动态规划
   * 函数的参数就是状态转移中会变化的量，也就是「状态」
   * 函数的返回值就是题目要求我们计算的量
   * dp表
   */
  fun fib2(N: Int): Int {
    // base case
    if (N < 2) return N
    val dp = IntArray(N + 1)
    dp[0] = 0
    dp[1] = 1
    // 状态： N -- 变化的量
    for (i in 2..N) {
      dp[i] = dp[i - 1] + dp[i - 2] //状态转移方程
    }
    return dp[N]
  }

  /**
   * 空间优化
   */
  fun fib1(N: Int): Int {
    if (N < 1) {
      return 0
    }
    if (N == 1 || N == 2) {
      return 1
    }
    var pre = 1
    var curr = 1
    for (i in 3..N) {
      val sum = pre + curr
      pre = curr
      curr = sum
    }
    return curr
  }

}

fun main() {
  val fib = Solution509().fib(4)
  println(fib)
}
