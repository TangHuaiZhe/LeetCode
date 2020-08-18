package dynamicProgramming

import kotlin.math.min

//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。
//
//
//
// 示例 1:
//
// 输入: coins = [1, 2, 5], amount = 11
//输出: 3
//解释: 11 = 5 + 5 + 1
//
// 示例 2:
//
// 输入: coins = [2], amount = 3
//输出: -1
//
//
//
// 说明:
//你可以认为每种硬币的数量是无限的。
// Related Topics 动态规划
// 👍 759 👎 0
//leetcode submit region begin(Prohibit modification and deletion)
class Solution322 {

  private var coinsLocal: IntArray? = null
  private var hashMap = HashMap<Int, Int>()

  fun coinChange(coins: IntArray, amount: Int): Int {
    coinsLocal = coins
    return dp(amount)
  }

  // 暴力递归， 超时
  // 加个hashMap备忘  任然非常耗时
  // 定义：要凑出金额 amount，至少要 dp(amount) 个硬币
  fun dp(amount: Int): Int {
    if (hashMap.containsKey(amount)) {
      return hashMap[amount]!!
    } else {
      if (amount == 0) return 0
      if (amount < 0) return -1
      var res = Int.MAX_VALUE
      // 做选择，选择需要硬币最少的那个结果
      for (coin in this.coinsLocal!!) {
        val subProblem = dp(amount - coin)
        if (subProblem == -1) continue
        res = min(res, 1 + dp(amount - coin))
      }
      res = if (res != Int.MAX_VALUE) res else -1
      hashMap[amount] = res
      return res
    }
  }

}

/**
 * 动态规划
 */
class Solution322_1 {

  fun coinChange(coins: IntArray, amount: Int): Int {
    // 把「状态」，也就是目标金额作为变量。
    // 不过 dp 函数体现在函数参数，而 dp 数组体现在数组索引

    // dp 数组的定义：当目标金额为 i 时，至少需要 dp[i] 枚硬币凑出。
    val dp = IntArray(amount + 1) {
      // 初始化
      amount + 1
    }
    // base case
    dp[0] = 0
    // 外层 for 循环在遍历所有状态的所有取值
    dp.forEachIndexed { index, _ ->
      // 内层 for 循环在求所有选择的最小值
      for (coin in coins) {
        // 子问题无解 pass
        if (index - coin < 0) continue
        // 状态转移
        dp[index] = min(dp[index], 1 + dp[index - coin])
      }
    }
    return if (dp[amount] == amount + 1) -1 else dp[amount]
  }

  fun coinChange1(coins: IntArray, amount: Int): Int {
    // 把「状态」，也就是目标金额作为变量。
    // 不过 dp 函数体现在函数参数，而 dp 数组体现在数组索引

    // dp 数组的定义：当目标金额为 i 时，至少需要 dp[i] 枚硬币凑出。
    val dp = IntArray(amount + 1)
    // 外层 for 循环在遍历所有状态的所有取值
    dp.forEachIndexed { index, _ ->
      dp[index] = if (index == 0) 0 else amount + 1
      // 内层 for 循环在求所有选择的最小值
      for (coin in coins) {
        // 子问题无解 pass
        if (index - coin < 0) continue
        // 状态转移
        dp[index] = min(dp[index], 1 + dp[index - coin])
      }
    }
    return if (dp[amount] == amount + 1) -1 else dp[amount]
  }
}

fun main() {
  val coinChange = Solution322().coinChange(intArrayOf(2), 0)
  println(coinChange)
  val coinChange1 = Solution322().coinChange(intArrayOf(1, 2, 5), 11)
  println(coinChange1)

  val coinChange2 = Solution322_1().coinChange(intArrayOf(2), 0)
  println(coinChange2)
  val coinChange3 = Solution322_1().coinChange(intArrayOf(1, 2, 5), 11)
  println(coinChange3)

}
