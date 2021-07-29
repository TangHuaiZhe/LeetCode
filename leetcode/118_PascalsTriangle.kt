/**
 *
给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
在「杨辉三角」中，每个数是它左上方和右上方的数的和。


示例 1:
输入: numRows = 5
输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

示例2:
输入: numRows = 1
输出: [[1]]


每个数字等于上一行的左右两个数字之和，可用此性质写出整个杨辉三角。
即第n行的第i个数等于第n−1行的第i−1个数和第i个数之和。
 **/

internal class Solution118 {
  fun generate(numRows: Int): List<List<Int>> {
    val result = ArrayList<ArrayList<Int>>()
    for (row in 0 until numRows) {
      val rowResult = ArrayList<Int>()
      //需要包含row
      for (index in 0..row) {
        if (index == 0 || index == row) {
          rowResult.add(1)
        } else {
          rowResult.add(result[row - 1][index] + result[row - 1][index - 1])
        }
      }
      result.add(rowResult)
    }
    return result
  }
}

fun main() {
  println(Solution118().generate(5))
}

