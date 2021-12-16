//给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
//
// 有效 二叉搜索树定义如下：
//
//
// 节点的左子树只包含 小于 当前节点的数。
// 节点的右子树只包含 大于 当前节点的数。
// 所有左子树和右子树自身必须也是二叉搜索树。
//
//
//
//
// 示例 1：
//
//
//输入：root = [2,1,3]
//输出：true
//
//
// 示例 2：
//
//
//输入：root = [5,1,4,null,null,3,6]
//输出：false
//解释：根节点的值是 5 ，但是右子节点的值是 4 。
//
//
// 提示：
//
//
// 树中节点数目范围在[1, 10⁴] 内
// -2³¹ <= Node.val <= 2³¹ - 1
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1345 👎 0
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

// 如果该二叉树的左子树不为空，则左子树上所有节点的值均小于它的根节点的值；
// 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
// 它的左右子树也为二叉搜索树。

internal class Solution98 {

  fun isValidBST(root: TreeNode?): Boolean {
    if (root == null) {
      return true
    }
    val low = Long.MIN_VALUE
    val upper = Long.MAX_VALUE
    // 左子树上所有节点的值均小于它的根节点的值 && 右子树上所有节点的值均大于它的根节点的值
    return help(root.left, low, root.`val`.toLong()) && help(root.right, root.`val`.toLong(), upper)
  }

  private fun help(root: TreeNode?, low: Long, upper: Long): Boolean {
    if (root == null) return true
    if (root.`val` <= low || root.`val` >= upper) {
      return false
    }
    return help(root.left, low, root.`val`.toLong()) && help(root.right, root.`val`.toLong(), upper)
  }
}

fun main() {

  val treeNode2 = TreeNode(2)
  val treeNode1 = TreeNode(1)
  val treeNode3 = TreeNode(3)
  treeNode2.left = treeNode1
  treeNode2.right = treeNode3
  println(Solution98().isValidBST(treeNode1))

}