package jzoffer

import java.util.HashSet




/**
 * author: i530643
 * created on: 3/4/21 1:54 PM
 * description:

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

fun main() {
  val nums = intArrayOf(2,3,1,0,2,5,3)
  val findRepeatNumber = findRepeatNumber(nums)
  println(findRepeatNumber)
}
