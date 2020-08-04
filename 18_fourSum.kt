/**
 * author: tang
 * created on: 2019-08-17 09:17
 * description:
 */

// 还是双指针


// 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
// 判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
//
//注意：
//
//答案中不可以包含重复的四元组。
//
//示例：
//
//给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//满足要求的四元组集合为：
//[
//[-1,  0, 0, 1],
//[-2, -1, 1, 2],
//[-2,  0, 0, 2]
//]

//和3数之和类似，
//先将数组排序固定两个元素在用两个指针，一个指向头，一个指向尾，看四数之和为多少，
// 太大了右指针左移，太小了左指针右移，因为有可能存在重复的数组，先将结果保存在set中，最后在转为list输出

fun fourSum(nums: IntArray, target: Int): List<List<Int>> {

  nums.sort()
  val ans = HashSet<List<Int>>()
  for (i in 0 until nums.size - 3) {

    for (j in i + 1 until nums.size - 2) {

      var left = j + 1 //左指针
      var right = nums.size - 1//右指针
      while (right > left) {
        val temp = nums[i] + nums[j] + nums[left] + nums[right]
        when {
          temp == target -> {
            ans.add(arrayListOf(nums[i], nums[j], nums[left], nums[right]))
            left += 1
            right -= 1
          }
          temp > target -> right -= 1 //太大了，右指针左移
          temp < target -> left += 1
        }
      }
    }
  }
  return ans.toList()
}

fun main() {
  val nums = intArrayOf(1, 0, -1, 0, -2, 2)
  val target = 0
  println(fourSum(nums, target))
  //[[-2, 0, 0, 2], [-2, -1, 1, 2], [-1, 0, 0, 1]]
}