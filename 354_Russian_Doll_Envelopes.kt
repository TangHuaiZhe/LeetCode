import kotlin.math.max

//给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
//
// 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
//
// 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
//
// 注意：不允许旋转信封。
//
//
// 示例 1：
//
//
//输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
//输出：3
//解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
//
// 示例 2：
//
//
//输入：envelopes = [[1,1],[1,1],[1,1]]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= envelopes.length <= 5000
// envelopes[i].length == 2
// 1 <= wi, hi <= 104
//
// Related Topics 数组 二分查找 动态规划 排序
// 👍 544 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 本质是求一个二维的最长递增子序列
 */
class Solution354 {
  fun maxEnvelopes(envelopes: Array<IntArray>): Int {
    envelopes.sortWith(Comparator { o1, o2 ->
      if (o1[0] == o2[0]) {
        o2[1] - o1[1]
      } else {
        o1[0] - o2[0]
      }
    })
    val height = IntArray(envelopes.size)
    envelopes.forEachIndexed { index, it ->
      println("${it[0]} ${it[1]}")
      height[index] = it[1]
    }

    return lengthOfLIS(height)
  }

  /**
   * 计算最长递增子序列
   */
  private fun lengthOfLIS(nums: IntArray): Int {
    //base case: dp数组都为1
    val dp = IntArray(nums.size) {
      1
    }

    for (i in nums.indices) {
      for (j in 0 until i) {
        if (nums[i] > nums[j]) {
          dp[i] = max(dp[i], dp[j] + 1)
        }
      }
    }
    return dp.max()!!
  }
}
//leetcode submit region end(Prohibit modification and deletion)

fun main() {
  val envelopes =
    arrayOf(intArrayOf(30, 50), intArrayOf(12, 2), intArrayOf(3, 4), intArrayOf(12, 15))
  val result = Solution354().maxEnvelopes(envelopes)
  println(result)
}