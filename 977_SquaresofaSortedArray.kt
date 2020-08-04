
/**
 * author: tang
 * created on: 2019-08-22 11:28
 * description:
 */

//给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
//
// 
//
//示例 1：
//
//输入：[-4,-1,0,3,10]
//输出：[0,1,9,16,100]
//示例 2：
//
//输入：[-7,-3,2,3,11]
//输出：[4,9,9,49,121]



//方法二：双指针
//思路
//
//因为数组 A 已经排好序了， 所以可以说数组中的负数已经按照平方值降序排好了，数组中的非负数已经按照平方值升序排好了。
//
//举一个例子，若给定数组为 [-3, -2, -1, 4, 5, 6]，数组中负数部分 [-3, -2, -1] 的平方为 [9, 4, 1]，
// 数组中非负部分 [4, 5, 6] 的平方为 [16, 25, 36]。
//
// 我们的策略就是从前向后遍历数组中的非负数部分，并且反向遍历数组中的负数部分。
//
//算法
//
//我们可以使用两个指针分别读取数组的非负部分与负数部分
// —— 指针 i 反向读取负数部分，
// 指针 j 正向读取非负数部分。
//
//那么，现在我们就在使用两个指针分别读取两个递增的数组了（按元素的平方排序）。
// 接下来，我们可以使用双指针的技巧合并这两个数组。





fun sortedSquares(array: IntArray): IntArray {
  return array.map { it*it }.sorted().toIntArray()
}

fun sortedSquares2(array: IntArray): IntArray {
  val size = array.size
  // 指针 j 正向读取非负数部分
  var j = 0
  while (j < size && array[j] < 0)
    j++
  // 指针 i 反向读取负数部分
  var i = j-1


  val ans = IntArray(size)
  // 比较两部分元素的大小并存入ans数组
  var t = 0
  while (i >= 0 && j < size) {
    if (array[i] * array[i] < array[j] * array[j]) {
      ans[t++] = array[i] * array[i]
      i--
    } else {
      ans[t++] = array[j] * array[j]
      j++
    }
  }

  // 剩余的元素
  while (i >= 0) {
    ans[t++] = array[i] * array[i]
    i--
  }
  while (j < size) {
    ans[t++] = array[j] * array[j]
    j++
  }
  return ans
}


fun main() {
  val list = listOf(-3, -2, -1, 4, 5, 6).toIntArray()
  sortedSquares2(list).forEach { print("$it ") }
}