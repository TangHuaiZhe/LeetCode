/**
 * author: i530643
 * created on: 2022/2/21 16:10
 * description: 二叉树中的最大路径和
 *
 *
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。

路径和 是路径中各节点值的总和。

给你一个二叉树的根节点 root ，返回其 最大路径和 。


 *
 */
internal class Solution124 {

  var maxSum = Int.MIN_VALUE

  fun maxPathSum(root: TreeNode?): Int {
    maxGain(root)
    return maxSum
  }

  fun maxGain(node: TreeNode?): Int {
    if (node == null) {
      return 0
    }

    // 递归计算左右子节点的最大贡献值
    // 只有在最大贡献值大于 0 时，才会选取对应子节点
    val leftGain = Math.max(maxGain(node.left), 0)
    val rightGain = Math.max(maxGain(node.right), 0)

    // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
    val priceNewpath = node.`val` + leftGain + rightGain

    // 更新答案
    maxSum = Math.max(maxSum, priceNewpath)

    // 返回节点的最大贡献值
    return node.`val` + Math.max(leftGain, rightGain)
  }
}

fun main() {
  val node1 = TreeNode(1)
  val node2 = TreeNode(2)
  val node3 = TreeNode(3)
  node1.left = node2
  node1.right = node3
  val sum = Solution124().maxPathSum(node1)
  println(sum)
}