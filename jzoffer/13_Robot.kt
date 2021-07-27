package jzoffer

/**
地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 **/

internal class Solution12 {
  fun movingCount(rows: Int, cols: Int, threshold: Int): Int {
    if (threshold < 0 || rows <= 0 || cols <= 0) {
      return 0
    }
    val visitedList = BooleanArray(rows * cols)

    return movingCountCore(threshold, rows, cols, 0, 0, visitedList)
  }

  private fun movingCountCore(
    threshold: Int,
    rows: Int,
    cols: Int,
    row: Int,
    col: Int,
    visitedList: BooleanArray
  ): Int {
    var count = 0
    if (check(threshold, rows, cols, row, col, visitedList)) {
      visitedList[row * cols + col] = true
      count =
        1 + movingCountCore(threshold, rows, cols, row - 1, col, visitedList) + movingCountCore(
          threshold,
          rows,
          cols,
          row + 1,
          col,
          visitedList
        ) + movingCountCore(threshold, rows, cols, row, col + 1, visitedList) + movingCountCore(
          threshold,
          rows,
          cols,
          row,
          col - 1,
          visitedList
        )
    }
    return count
  }

  private fun check(
    threshold: Int,
    rows: Int,
    cols: Int,
    row: Int,
    col: Int,
    visitedList: BooleanArray
  ): Boolean {
    return row in 0 until rows && col in 0 until cols && (getDigitalSum(row) + getDigitalSum(col) <= threshold) && !visitedList[row * cols + col]
  }

  private fun getDigitalSum(num: Int): Int {
    var sum = 0
    var number = num
    while (number > 0) {
      sum += number % 10
      number /= 10
    }
    return sum
  }
}

fun main() {
  println(Solution12().movingCount(2, 3, 1))
  println(Solution12().movingCount(3, 1, 0))
}