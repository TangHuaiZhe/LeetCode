/**
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 */
class Solution100 {
  fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null && q != null) return false
    if (p != null && q == null) return false
    return if (p != null && q != null) {
      p.`val` == q.`val` && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    } else {
      true
    }
  }
}

fun main() {
  val node1 = TreeNode(1)
  val node2 = TreeNode(2)
  val node3 = TreeNode(3)

  node1.left = node3
  node1.right = node2

  val node11 = TreeNode(1)
  val node21 = TreeNode(2)
  val node31 = TreeNode(3)

  node11.left = node31
  node11.right = node21

  println("===")
  val sameTree = Solution100().isSameTree(node1, node11)
  println(sameTree)
}