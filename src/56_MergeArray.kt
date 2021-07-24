import java.util.Arrays
import kotlin.math.max

//给出一个区间的集合，请合并所有重叠的区间。
//
// 示例 1:
//
// 输入: [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//
//
// 示例 2:
//
// 输入: [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
// Related Topics 排序 数组
// 👍 544 👎 0

internal class Solution56 {
  fun merge(intervals: Array<IntArray>): Array<IntArray> {
    // 先按照区间起始位置排序
    Arrays.sort(intervals) { v1: IntArray, v2: IntArray -> v1[0] - v2[0] }
    // 遍历区间
    val res = Array(intervals.size) { IntArray(2) }
    var index = -1
    for (interval in intervals) {
      // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
      // 则不合并，直接将当前区间加入结果数组。
      if (index == -1 || interval[0] > res[index][1]) {
        index++
        res[index] = interval
      } else {
        // 反之将当前区间合并至结果数组的最后区间
        res[index][1] = max(res[index][1], interval[1])
      }
    }
    return Arrays.copyOf(res, index + 1)
  }
}

fun main() {

}