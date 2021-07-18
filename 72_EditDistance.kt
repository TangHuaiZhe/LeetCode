import kotlin.math.min

//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作：
//
//
// 插入一个字符
// 删除一个字符
// 替换一个字符
//
//
//
//
// 示例 1：
//
//
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
//
//
// 示例 2：
//
//
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
//
//
//
//
// 提示：
//
//
// 0 <= word1.length, word2.length <= 500
// word1 和 word2 由小写英文字母组成
//
// Related Topics 字符串 动态规划
// 👍 1703 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class Solution72 {

  private lateinit var word1: String
  private lateinit var word2: String

  private val memo: HashMap<Pair<Int, Int>, Int> = HashMap()

  //word1 -> word2
  fun minDistance(s1: String, s2: String): Int {
    this.word1 = s1
    this.word2 = s2
    return calculate(word1.length - 1, word2.length - 1)
  }

  private fun calculate(i: Int, j: Int): Int {

    if (Pair(i, j) in memo) {
      return memo[Pair(i, j)]!!
    }

    //base case
    if (i == -1) return j + 1
    if (j == -1) return i + 1

    if (word1[i] == word2[j]) {
      memo[Pair(i, j)] = calculate(i - 1, j - 1)
    } else {
      memo[Pair(i, j)] = min(
        calculate(i, j - 1) + 1, // 插入
        min(
          calculate(i - 1, j) + 1, // 删除
          calculate(i - 1, j - 1) + 1 // 替换
        )
      )
    }
    return memo[Pair(i, j)]!!
  }
}

//leetcode submit region end(Prohibit modification and deletion)
fun main() {
  val minDistance = Solution72().minDistance("rad", "apple")
  println(minDistance)
}