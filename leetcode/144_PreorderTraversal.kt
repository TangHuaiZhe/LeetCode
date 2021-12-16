class Solution144 {

  fun preorderTraversal(root: TreeNode?): List<Int> {
    val res = ArrayList<Int>()
    preOrder(root, res)
    return res
  }

  fun preOrder(root: TreeNode?, res: ArrayList<Int>) {
    if (root == null) {
      return
    }
    res.add(root.`val`) // add val into res
    preOrder(root.left, res)
    preOrder(root.right, res)
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

  val lists = Solution144().preorderTraversal(node1)
  println(lists)

}