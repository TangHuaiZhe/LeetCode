import java.util.Arrays
import java.util.HashMap

//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
//
//
//
// 示例 1:
//
// 输入: [3,2,3]
//输出: 3
//
// 示例 2:
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
//
// Related Topics 位运算 数组 分治算法
// 👍 688 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class Solution169 {
  private fun countNums(nums: IntArray): Map<Int, Int?> {
    val counts: MutableMap<Int, Int?> = HashMap()
    for (num in nums) {
      if (!counts.containsKey(num)) {
        counts[num] = 1
      } else {
        counts[num] = counts[num]!! + 1
      }
    }
    return counts
  }

  fun majorityElement(nums: IntArray): Int {
    val counts = countNums(nums)
    var majorityEntry: Map.Entry<Int, Int?>? = null
    for (entry in counts.entries) {
      if (majorityEntry == null || entry.value!! > majorityEntry.value!!) {
        majorityEntry = entry
      }
    }
    return majorityEntry!!.key
  }

  fun majorityElement1(nums: IntArray): Int {
    Arrays.sort(nums)
    return nums[nums.size / 2]
  }

}

internal class Solution169_1 {
  private fun countInRange(nums: IntArray, num: Int, lo: Int, hi: Int): Int {
    var count = 0
    for (i in lo..hi) {
      if (nums[i] == num) {
        count++
      }
    }
    return count
  }

  private fun majorityElementRec(nums: IntArray, lo: Int, hi: Int): Int {
    // base case; the only element in an array of size 1 is the majority
    // element.
    if (lo == hi) {
      return nums[lo]
    }

    // recurse on left and right halves of this slice.
    val mid = (hi - lo) / 2 + lo
    val left = majorityElementRec(nums, lo, mid)
    val right = majorityElementRec(nums, mid + 1, hi)

    // if the two halves agree on the majority element, return it.
    if (left == right) {
      return left
    }

    // otherwise, count each element and return the "winner".
    val leftCount = countInRange(nums, left, lo, hi)
    val rightCount = countInRange(nums, right, lo, hi)
    return if (leftCount > rightCount) left else right
  }

  fun majorityElement(nums: IntArray): Int {
    return majorityElementRec(nums, 0, nums.size - 1)
  }
}

//leetcode submit region end(Prohibit modification and deletion)
fun main() {
  var nums = intArrayOf(2, 2, 1, 1, 1, 2, 2)
//  nums = intArrayOf(-1, -1, 2147483647)
  val result = Solution169().majorityElement1(nums)
  println(result)
}