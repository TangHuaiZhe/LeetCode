import java.util.Deque
import java.util.LinkedList

//给定一个二叉树，找出其最小深度。
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
// 说明：叶子节点是指没有子节点的节点。
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：2
//
//
// 示例 2：
//
//
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
//
//
//
//
// 提示：
//
//
// 树中节点数的范围在 [0, 105] 内
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树
// 👍 633 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class TreeNode(var `val`: Int) {
  var left: TreeNode? = null
  var right: TreeNode? = null
}

class Solution111 {
  fun minDepth(root: TreeNode?): Int {
    if (root == null) {
      return 0
    }
    val deque: Deque<TreeNode?> = LinkedList()
    deque.offer(root)
    var level = 1
    while (!deque.isEmpty()) {
      val size = deque.size
      //不能用foreach 要翻车 不能一边offer，一边又遍历，只应该遍历一层
      for (i in 0 until size) {
        val cur = deque.poll()
        if (cur!!.right == null && cur.left == null) {
          return level
        }
        if (cur.left != null) {
          deque.offer(cur.left)
        }
        if (cur.right != null) {
          deque.offer(cur.right)
        }
      }
      level++
    }
    return level
  }
}

fun main() {
  //[1,2,3,4,null,null,5]
  val node1 = TreeNode(1)
  val node2 = TreeNode(2)
  val node3 = TreeNode(3)
  val node4 = TreeNode(4)
  val node5 = null
  val node6 = null
  val node7 = TreeNode(5)

  node1.left = node2
  node1.right = node3
  node2.left = node4
  node2.right = node5
  node3.left = node6
  node3.right = node7

  val minDepth = Solution111().minDepth(node1)
  println(minDepth)
}