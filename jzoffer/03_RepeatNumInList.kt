package jzoffer

import java.util.HashSet

/**

找出数组中重复的数字。
在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

示例 1：
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3
 */
fun findRepeatNumber(nums: IntArray): Int {
  val set: MutableSet<Int> = HashSet()
  var repeat = -1
  for (num in nums) {
    if (!set.add(num)) {
      repeat = num
      break
    }
  }
  return repeat
}

fun findRepeatNumber2(nums: IntArray): Int {
  var result = -1
  nums.forEachIndexed { index, _ ->
    if (nums[index] == index) {
    //说明此数字已在对应索引位置，无需交换，因此跳过
    }else if (nums[nums[index]] == nums[index]) {
      //代表索引 nums[i]处和索引 i 处的元素值都为 nums[i] ，即找到一组重复值，返回此值 nums[i]
      result = nums[index]
    } else {
      //否则， 交换索引为 i 和 nums[i] 的元素值，将此数字交换至对应索引位置
      val temp = nums[nums[index]] // nums[nums[index]]
      nums[nums[index]] = nums[index]
      nums[index] = temp
    }
  }
  return result
}

fun main() {
  val nums = intArrayOf(2, 3, 1, 0, 2, 5, 3)
  val findRepeatNumber = findRepeatNumber(nums)
  println(findRepeatNumber)
  println(findRepeatNumber2(intArrayOf(0, 1, 2, 3, 4, 11, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)))
}
