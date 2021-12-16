import java.util.ArrayList

internal class Solution99 {
  fun recoverTree(root: TreeNode?) {
    val list: ArrayList<TreeNode> = ArrayList()
    //中序遍历二叉树，并将遍历的结果保存到list中
    dfs(root, list)
    var x: TreeNode? = null
    var y: TreeNode? = null
    //扫面遍历的结果，找出可能存在错误交换的节点x和y
    for (i in 0 until list.size - 1) {
      if (list[i].`val` > list[i + 1].`val`) {
        // 第二个错误的节点，以遍历到最后为准
        y = list[i + 1]
        if (x == null) {
          // 第一个错误的节点只要找到就确定了
          x = list[i]
        }
      }
    }
    //如果x和y不为空，则交换这两个节点值，恢复二叉搜索树
    if (x != null && y != null) {
      val tmp = x.`val`
      x.`val` = y.`val`
      y.`val` = tmp
    }
  }

  private fun dfs(node: TreeNode?, list: MutableList<TreeNode>) {
    if (node == null) {
      return
    }
    dfs(node.left, list)
    list.add(node)
    dfs(node.right, list)
  }
}

fun main() {
  val node1 = TreeNode(1)
  val node2 = TreeNode(2)
  val node3 = TreeNode(3)

  node1.left = node3
  node1.right = node2
  println(node1)
  println("===")
  Solution99().recoverTree(node1)
  println(node1)
}