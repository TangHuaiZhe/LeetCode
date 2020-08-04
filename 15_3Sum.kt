
/**
 * author: tang
 * created on: 2019-11-06 11:13
 * description:
 *
 *
 *
给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
[-1, 0, 1],
[-1, -1, 2]
]
 */
fun threeSum(nums: IntArray): List<List<Int>> {
  val result = ArrayList<ArrayList<Int>>()
  nums.sort()
  for (middle in 1 until nums.size) {
    var first = 0
    var last = nums.size - 1
    while (first <= middle - 1 && last >= middle + 1) {
      val sum = nums[first] + nums[middle] + nums[last]
      when {
        sum > 0 -> last--
        sum < 0 -> first++
        sum == 0 -> {
          val list = arrayListOf(nums[first], nums[middle], nums[last])
          if (list !in result) {
            println("add list $list")
            result.add(list)
          }
          first++
        }
      }
    }
  }
  return result
}

fun threeSum1(nums: IntArray): List<List<Int>> {
  val list = arrayListOf<List<Int>>()
  nums.sort()
  if (nums.size < 3) return list
  nums.forEachIndexed { index, value ->
    if (value > 0) return@forEachIndexed
    if (index == 0 || value != nums[index - 1]) {   // 去重
      var L = index + 1
      var R = nums.size - 1
      while (L < R) {
        val sum = value + nums[L] + nums[R]
        when {
          sum == 0 -> {
            list.add(arrayListOf(value, nums[L], nums[R]))
            while (L < R && nums[L] == nums[L + 1]) L++
            while (L < R && nums[R] == nums[R - 1]) R--
            L++
            R--
          }
          sum < 0 -> L++
          sum > 0 -> R--
        }
      }
    }
  }
  return list
}

fun main() {
  var nums = intArrayOf(-1, 0, 1, 2, -1, -4)
//  nums = intArrayOf(0, 0, 0, 0)
//  nums = intArrayOf(3, 0, -2, -1, 1, 2)
  val threeSum = threeSum(nums)
  println(threeSum)
}