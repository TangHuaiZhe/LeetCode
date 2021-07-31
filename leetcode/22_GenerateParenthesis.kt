/**

数字n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
示例 1：
输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]

示例 2：
输入：n = 1
输出：["()"]
 */

internal class Solution22 {
  fun generateParenthesis(n: Int): List<String> {
    val result = ArrayList<String>()
    if (n == 0) {
      return result
    }
    dfs("", n, n, result)
    return result
  }

  /**
   * @param curStr 当前递归得到的结果
   * @param left   左括号还有几个可以使用
   * @param right  右括号还有几个可以使用
   * @param res    结果集
   * https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
  */
  fun dfs(current: String, left: Int, right: Int, result: ArrayList<String>) {
    if (left > right) {
      return
    }
    if (right == 0 && left == 0) {
      result.add(current)
    }
    if (left > 0) {
      dfs("$current(", left - 1, right, result)
    }
    if (right > 0) {
      dfs("$current)", left, right - 1, result)
    }
  }
}

fun main() {
  println(Solution22().generateParenthesis(4))
  println(Solution22().generateParenthesis(3))
  println(Solution22().generateParenthesis(2))
  println(Solution22().generateParenthesis(1))
}