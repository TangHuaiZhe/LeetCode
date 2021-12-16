//给定一个二叉树的根节点 root ，返回它的 中序 遍历。
//
//
//
// 示例 1：
//
//
//输入：root = [1,null,2,3]
//输出：[1,3,2]
//
//
// 示例 2：
//
//
//输入：root = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [1]
//输出：[1]
//
//
// 示例 4：
//
//
//输入：root = [1,2]
//输出：[2,1]
//
//
// 示例 5：
//
//
//输入：root = [1,null,2]
//输出：[1,2]
//
//
//
//
// 提示：
//
//
// 树中节点数目在范围 [0, 100] 内
// -100 <= Node.val <= 100
//
//
//
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
// Related Topics 栈 树 深度优先搜索 二叉树 👍 1204 👎 0
//leetcode submit region begin(Prohibit modification and deletion)
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
//二叉树的中序遍历：按照访问左子树——根节点——右子树的方式遍历这棵树
internal class Solution94 {
  fun inorderTraversal(root: TreeNode?): List<Int> {
    if (root == null){
      return emptyList()
    }
    val res = ArrayList<Int>()
    res.addAll(inorderTraversal(root.left))
    res.add(root.`val`)
    res.addAll(inorderTraversal(root.right))
    return res
  }

  fun inorderTraversal1(root: TreeNode?): List<Int> {
    val res: ArrayList<Int> = ArrayList()
    inorder(root, res)
    return res
  }

  fun inorder(root: TreeNode?, res: ArrayList<Int>) {
    if (root == null) {
      return
    }
    inorder(root.left, res)
    res.add(root.`val`) // add val into res
    inorder(root.right, res)
  }

}

fun main() {
  val node1 = TreeNode(1)
  val node2 = TreeNode(2)
  val node3 = TreeNode(3)
  node1.right = node2
  node2.left = node3
  val intList = Solution94().inorderTraversal1(node1)
  println(intList)
}