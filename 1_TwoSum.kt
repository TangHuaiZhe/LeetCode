import java.util.HashMap

/**
 * author: tang
 * created on: 2019-05-10 15:48
 * description:
 */
// 空间换时间
//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
//你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
//给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]

//保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。
//
//一个简单的实现使用了两次迭代。
// 在第一次迭代中，我们将每个元素的值和它的索引添加到表中。
// 然后，在第二次迭代中，我们将检查每个元素所对应的目标元素（target - nums[i]target−nums[i]）是否存在于表中。
// 注意，该目标元素不能是 nums[i]nums[i] 本身！

fun twoSum(nums: IntArray, target: Int): IntArray {
  val map = HashMap<Int, Int>()
  for (i in nums.indices) {
    map[nums[i]] = i
  }
  for (i in nums.indices) {
    val complement = target - nums[i]
    if (map.containsKey(complement) && map[complement] != i) {
      return intArrayOf(i, map[complement]!!)
    }
  }
  throw IllegalArgumentException("No two sum solution")
}

fun twoSum2(nums: IntArray, target: Int): IntArray {
  val map = HashMap<Int, Int>()
  for (i in nums.indices) {
    val complement = target - nums[i]
    if (map.containsKey(complement) && map[complement] != i) {
      return intArrayOf(i, map[complement]!!)
    }
    map[nums[i]] = i
  }
  throw IllegalArgumentException("No two sum solution")
}

fun main() {
  val nums = intArrayOf(3, 3)
  val target = 6
  val twoSum = twoSum2(nums, target)
  println(twoSum[0])
  println(twoSum[1])
}