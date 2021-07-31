package jzoffer

/**
 * 输入两棵二叉树A和B，判断B是不是A的子结构。
 * (约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 */

class Solution26 {

  class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
  }

  fun isSubStructure(A: jzoffer.TreeNode?, B: jzoffer.TreeNode?): Boolean {
    if (A == null || B == null) return false
    return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B)
  }

  private fun dfs(a: jzoffer.TreeNode?, b: jzoffer.TreeNode?): Boolean {
    if (b == null) {
      return true
    }

    if (a == null) {
      return false
    }

    return dfs(a.left, b.left) && dfs(a.right, b.right) && a.`val` == b.`val`
  }
}

fun main() {
  val treeNode1 = TreeNode(3)
  val treeNode2 = TreeNode(4)
  val treeNode3 = TreeNode(5)
  val treeNode4 = TreeNode(1)
  val treeNode5 = TreeNode(2)

  treeNode1.left = treeNode2
  treeNode1.right = treeNode3

  treeNode2.left = treeNode4
  treeNode2.right = treeNode5

  val treeNode11 = TreeNode(4)
  val treeNode12 = TreeNode(1)
  treeNode11.left = treeNode12


  println(Solution26().isSubStructure(treeNode1, treeNode11))
}