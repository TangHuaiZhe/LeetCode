package leetcode

/**
 * author: tang
 * created on: 2019-08-17 08:22
 * description:
 */

//给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
//
//不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
//
//元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

fun removeElement(nums: IntArray, target: Int): Int {
  var realIndex = 0
  nums.forEach { value ->
    if (value != target) {
      nums[realIndex] = value
      realIndex++
    }
  }
  return realIndex
}

fun main() {
  val nums = intArrayOf(3, 2, 2, 3)
  val target = 3
  println(removeElement(nums, target))
}