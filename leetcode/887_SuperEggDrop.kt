import kotlin.math.max
import kotlin.math.min

//你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N 共有 N 层楼的建筑。
//
// 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
//
// 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
//
// 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
//
// 你的目标是确切地知道 F 的值是多少。
//
// 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
//
//
//
//
//
//
// 示例 1：
//
// 输入：K = 1, N = 2
//输出：2
//解释：
//鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
//否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
//如果它没碎，那么我们肯定知道 F = 2 。
//因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
//
//
// 示例 2：
//
// 输入：K = 2, N = 6
//输出：3
//
//
// 示例 3：
//
// 输入：K = 3, N = 14
//输出：4
//
//
//
//
// 提示：
//
//
// 1 <= K <= 100
// 1 <= N <= 10000
//
// Related Topics 数学 二分查找 动态规划
// 👍 463 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
internal class Solution887 {

  val memo = hashMapOf<Pair<Int, Int>, Int>()

  // 超时
  // 在最坏的情况 K个鸡蛋 N层楼 最少需要扔多少次鸡蛋
  fun superEggDrop(eggNum: Int, floors: Int): Int {
    // 只剩下一个鸡蛋 只能线性扫描
    if (eggNum == 1) return floors
    // 剩下0个楼层没扫描 返回0
    if (floors == 0) return 0

    if (memo.containsKey(Pair(eggNum, floors))) {
      return memo[Pair(eggNum, floors)]!!
    }

    var i = 1 // 丢鸡蛋的楼层
    var res = Int.MAX_VALUE
    // 从第1层开始丢鸡蛋，推导出之后每一层至少需要丢几次鸡蛋才能确定鸡蛋正好碎掉的楼层
    while (i < floors + 1) {
      res = min(res, max( //最坏的情况
          superEggDrop(eggNum, floors - i), // 鸡蛋没碎，鸡蛋数量不变，楼层需要变为【i+1。。floors】，一共floors-i层
          superEggDrop(eggNum - 1, i - 1) // 碎了，鸡蛋数量-1，楼层变为【1。。i-1】，共i-1层楼
      ) + 1)// 碎没碎 操作数都是+1
      i++
    }
    memo[Pair(eggNum, floors)] = res
    return res
  }
}
//leetcode submit region end(Prohibit modification and deletion)

fun main() {
  var superEggDrop = Solution887().superEggDrop(1, 2)
  println(superEggDrop)

  superEggDrop = Solution887().superEggDrop(2, 6)
  println(superEggDrop)

  superEggDrop = Solution887().superEggDrop(3, 14)
  println(superEggDrop)

}