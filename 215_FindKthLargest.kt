import java.util.PriorityQueue
import kotlin.random.Random

//在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
//
// 示例 1:
//
// 输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
//
//
// 示例 2:
//
// 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4
//
// 说明:
//
// 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
// Related Topics 堆 分治算法
// 👍 643 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

class Solution215 {
  fun findKthLargest(nums: IntArray, k: Int): Int {
    val len = nums.size
    var left = 0
    var right = len - 1

    // 转换一下，第 k 大元素的索引是 len - k
    val target = len - k
    while (true) {
      val index = partition(nums, left, right)
      when {
        index == target -> {
          return nums[index]
        }
        index < target -> {
          left = index + 1
        }
        else -> {
          right = index - 1
        }
      }
    }
  }

  /**
   * 在数组 nums 的子区间 [left, right] 执行 partition 操作，返回 nums[left] 排序以后应该在的位置
   * 在遍历过程中保持循环不变量的语义
   * 1、[left + 1, j] < nums[left]
   * 2、(j, i] >= nums[left]
   */
  fun partition(nums: IntArray, left: Int, right: Int): Int {

    if (right > left) {
      val randomIndex = left + 1 + Random.nextInt(right - left)
      swap(nums, left, randomIndex)
    }

    val pivot = nums[left]
    var j = left
    for (i in left + 1..right) {
      if (nums[i] < pivot) {
        // 小于 pivot 的元素都被交换到前面
        j++
        swap(nums, j, i)
      }
    }
    // 在之前遍历的过程中，满足 [left + 1, j] < pivot，并且 (j, i] >= pivot
    swap(nums, j, left)
    // 交换以后 [left, j - 1] < pivot, nums[j] = pivot, [j + 1, right] >= pivot
    return j
  }

  private fun swap(nums: IntArray, index1: Int, index2: Int) {
    val temp = nums[index1]
    nums[index1] = nums[index2]
    nums[index2] = temp
  }

  fun findKthLargest1(nums: IntArray, k: Int): Int {
    val len = nums.size
    // 使用一个含有 len 个元素的最小堆，默认是最小堆，可以不写 lambda 表达式：(a, b) -> a - b
    val minHeap = PriorityQueue(len,
        Comparator { a: Int, b: Int -> a - b })
    for (i in 0 until len) {
      minHeap.add(nums[i])
    }
    for (i in 0 until len - k) {
      minHeap.poll()
    }
    return minHeap.peek()
  }
}

fun main() {
  val nums = intArrayOf(2, 1, 5, 2, 6, 9, 1, 0, 23)
  var findKthLargest = Solution215().findKthLargest(nums, 2)
  println(findKthLargest)
  findKthLargest = Solution215().findKthLargest(nums, 3)
  println(findKthLargest)

  findKthLargest = Solution215().findKthLargest(nums, 3)
  println(findKthLargest)

}



