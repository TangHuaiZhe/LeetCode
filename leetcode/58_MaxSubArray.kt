import kotlin.math.max

/**
 *
 *
最大子序和

给定一个整数数组 nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例 1：
输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组[4,-1,2,1] 的和最大，为6 。

示例 2：
输入：nums = [1]
输出：1
 *
 * 动态规划
 *
 */

internal class Solution58 {
  fun maxSubArray(nums: IntArray): Int {
    //dp数组的定义: dp[i]==索引i结尾的子序列的和
    //递推公式: dp[i] = max(dp[i-1]+nums[i],nums[i])
    val n = nums.size
    val dp = IntArray(n)
    dp[0] = nums[0]
    var maxResult = nums[0]
    for (index in 1 until n) {
      dp[index] = max(dp[index - 1] + nums[index], nums[index])
      maxResult = max(dp[index], maxResult)
    }
    return maxResult
  }

  fun maxSubArray2(nums: IntArray): Int {
    //dp数组的定义: dp[i]==索引i结尾的子序列的和
    //递推公式: dp[i] = max(dp[i-1]+nums[i],nums[i])
    //dp[i]只和dp[i-1]有关系，因此可以优化
    val n = nums.size
    var maxResult = nums[0]
    var pre = nums[0]
    for (index in 1 until n) {
      val current = max(pre + nums[index], nums[index])
      pre = current
      maxResult = max(current, maxResult)
    }
    return maxResult
  }
}

fun main() {
  println(Solution58().maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
  println(Solution58().maxSubArray(intArrayOf(1)))
  println(Solution58().maxSubArray(intArrayOf(0)))
  println(Solution58().maxSubArray(intArrayOf(-1)))
  println(Solution58().maxSubArray(intArrayOf(-100000)))


  println(Solution58().maxSubArray2(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
  println(Solution58().maxSubArray2(intArrayOf(1)))
  println(Solution58().maxSubArray2(intArrayOf(0)))
  println(Solution58().maxSubArray2(intArrayOf(-1)))
  println(Solution58().maxSubArray2(intArrayOf(-100000)))

}