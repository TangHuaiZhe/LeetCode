package jzoffer

internal class Solution {
  fun exist(board: Array<CharArray>, word: String): Boolean {
    val words = word.toCharArray()
    for (i in board.indices) {
      for (j in 0 until board[0].size) {
        if (dfs(board, words, i, j, 0)) return true
      }
    }
    return false
  }

  private fun dfs(board: Array<CharArray>, word: CharArray, i: Int, j: Int, k: Int): Boolean {
    // 递归终止条件
    if (i >= board.size || i < 0 || j >= board[0].size || j < 0 || board[i][j] != word[k]) return false
    if (k == word.size - 1) return true

    //代表此元素已访问过，防止之后搜索时重复访问
    board[i][j] = '\u0000'

    // 搜索下一单元格： 朝当前元素的 上、下、左、右 四个方向开启下层递归
    // 使用 或 连接 （代表只需找到一条可行路径就直接返回，不再做后续 DFS ）
    val res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
      dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1)
    // 还原当前矩阵元素： 将 board[i][j] 元素还原至初始值，即 word[k]
    board[i][j] = word[k]
    return res
  }
}

internal class Solution1 {

  var pathLength = 0

  fun exist(board: Array<CharArray>, word: String): Boolean {

    if (word.isEmpty() || board.isEmpty()) {
      return false
    }

    val rows = board.size
    val cols = board[0].size
    val visitedList = BooleanArray(rows * cols)

    for (row in 0 until rows) {
      for (col in 0 until cols) {
        val hasPathCore = hasPathCore(board, rows, cols, row, col, word, visitedList)
        pathLength = 0
        if (hasPathCore) {
          return true
        }
      }
    }

    return false
  }

  private fun hasPathCore(
    board: Array<CharArray>,
    rows: Int,
    cols: Int,
    row: Int,
    col: Int,
    word: String,
    visitedList: BooleanArray
  ): Boolean {
    if (pathLength == word.length) {
      return true
    }
    var hasPath = false

    if (row in 0 until rows &&
      col in 0 until cols &&
      board[row][col] == word[pathLength] &&
      !visitedList[row * cols + col]
    ) {
      pathLength++
      visitedList[row * cols + col] = true
      hasPath = hasPathCore(board, rows, cols, row, col - 1, word, visitedList) ||
        hasPathCore(board, rows, cols, row - 1, col, word, visitedList) ||
        hasPathCore(board, rows, cols, row, col + 1, word, visitedList) ||
        hasPathCore(board, rows, cols, row + 1, col, word, visitedList)

      if (!hasPath) {
        pathLength--
        visitedList[row * cols + col] = false
      }
    }
    return hasPath
  }
}

fun main() {
  val board1 = arrayOf(
    charArrayOf('A', 'B', 'C', 'E'),
    charArrayOf('S', 'F', 'C', 'S'),
    charArrayOf('A', 'D', 'E', 'E'),
  )
  val word = "ABCCED"
  val exist = Solution1().exist(board1, word)
  println(exist)

  val board2 = arrayOf(
    charArrayOf('a', 'b'),
    charArrayOf('c', 'd')
  )
  val word2 = "abcd"
  println(Solution1().exist(board2, word2))

  val board3 = arrayOf(
    charArrayOf('a', 'a')
  )
  val word3 = "aaa"
  println(Solution1().exist(board3, word3))
}