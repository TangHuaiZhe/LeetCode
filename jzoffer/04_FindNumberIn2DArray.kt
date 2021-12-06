package jzoffer

/**
在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

示例:

现有矩阵 matrix 如下：

[
[1,   4,  7, 11, 15],
[2,   5,  8, 12, 19],
[3,   6,  9, 16, 22],
[10, 13, 14, 17, 24],
[18, 21, 23, 26, 30]
]

给定 target = 5，返回 true。

给定 target = 20，返回 false。


限制：

0 <= n <= 1000
0 <= m <= 1000




---------------------------------

若数组为空，返回 false

重复下列步骤，直到行下标或列下标超出边界
获得当前下标位置的元素 num

如果 num 和 target 相等，返回 true
如果 num 大于 target，列下标减 1
如果 num 小于 target，行下标加 1

循环体执行完毕仍未找到元素等于 target ，说明不存在这样的元素，返回 false`
 **/

fun findNumberIn2DArray(matrix: Array<IntArray>, target: Int): Boolean {
  if (matrix.isEmpty() || matrix[0].isEmpty()) {
    return false;
  }

  var row = 0
  var col = matrix[0].size - 1
  while (row < matrix.size && col >= 0) {
    when {
      matrix[row][col] == target -> {
        return true
      }
      matrix[row][col] > target -> {
        col--
      }
      else -> {
        row++
      }
    }
  }
  return false
}

fun main() {
  val arrayList = arrayOf(
    intArrayOf(1, 4, 7, 11, 15),
    intArrayOf(2, 5, 8, 12, 19),
    intArrayOf(3, 6, 9, 16, 22),
    intArrayOf(10, 13, 14, 17, 24),
    intArrayOf(18, 21, 23, 26, 30)
  )
  val findNumberIn2DArray = findNumberIn2DArray(arrayList, 13)
  println(findNumberIn2DArray)
  println(findNumberIn2DArray(arrayOf(intArrayOf(-5)), -5))
  println(findNumberIn2DArray(arrayOf(intArrayOf(1,1)), 2))

}