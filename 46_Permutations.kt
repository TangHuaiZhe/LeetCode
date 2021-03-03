
import java.util.LinkedList


//给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
// 示例:
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
// Related Topics 回溯算法
// 👍 1166 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

var res: MutableList<List<Int>> = LinkedList()

/* 主函数，输入一组不重复的数字，返回它们的全排列 */
fun permute(nums: IntArray): List<List<Int?>>? {
  // 记录「路径」
  val track = LinkedList<Int>()
  backtrack(nums, track)
  return res
}

/**
 * 回溯套路
 */
// 路径：记录在 track 中
// 选择列表：nums 中不存在于 track 的那些元素
// 结束条件：nums 中的元素全都在 track 中出现
fun backtrack(nums: IntArray, track: LinkedList<Int>) {
  // 触发结束条件
  if (track.size == nums.size) {
    res.add(LinkedList(track))
    return
  }
  for (i in nums.indices) {
    // 排除不合法的选择
    if (track.contains(nums[i])) continue
    // 做选择
    track.add(nums[i])
    // 进入下一层决策树
    backtrack(nums, track)
    // 取消选择
    track.removeLast()
  }
}

//leetcode submit region end(Prohibit modification and deletion)

fun main() {
  val permute = permute(intArrayOf(1, 2, 3))
  println(permute)
}