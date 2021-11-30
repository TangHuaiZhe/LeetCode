package jzoffer

/**
把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。

例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。

示例 1：
输入：[3,4,5,1,2]
输出：1

示例 2：
输入：[2,2,2,0,1]
输出：0


二分


 **/

fun minArray(numbers: IntArray): Int {
  var index1 = 0
  var indexMid = index1 // 考虑为什么初始化为index1  因为原数组的旋转问题
  var index2 = numbers.size - 1
  while (numbers[index1] >= numbers[index2]) {

    if (index2 - index1 == 1) {
      indexMid = index2
      break
    }

    indexMid = (index2 + index1) / 2
    // 这种情况是考虑类似这种数组 [1, 0, 1, 1, 1] 和[1,1,1,0,1]都可以使[0,1,1,1,1]的旋转
    // 原来的判断逻辑就没用了
    if (numbers[index2] == numbers[index1] && numbers[indexMid] == numbers[index1]) {
      return numbers.min()!!
    }

    if (numbers[indexMid] >= numbers[index1]) {
      index1 = indexMid
    } else if (numbers[indexMid] <= numbers[index1]) {
      index2 = indexMid
    }
  }

  return numbers[indexMid]
}

fun main() {
  // println(minArray(intArrayOf(3, 4, 5, 1, 2)))
  // println(minArray(intArrayOf(1, 0, 1, 1, 1)))
  println(minArray(intArrayOf(2, 2, 2, 0, 1)))
}