import java.util.Deque
import java.util.LinkedList

// 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
// 广度优先遍历 BFS
class Solution102 {
  fun levelOrder(root: TreeNode?): List<List<Int>> {
    val result = ArrayList<ArrayList<Int>>()
    if (root == null) return result
    //BFS
    val deque: Deque<TreeNode?> = LinkedList()
    deque.offer(root)
    while (!deque.isEmpty()) {
      val size = deque.size
      val tempList = ArrayList<Int>()
      //不能用foreach 要翻车 不能一边offer，一边又遍历，只应该遍历一层
      for (i in 0 until size) {
        val cur = deque.poll()
        tempList.add(cur!!.`val`)
        if (cur.left != null) {
          deque.offer(cur.left)
        }
        if (cur.right != null) {
          deque.offer(cur.right)
        }
      }
      result.add(tempList)
    }
    return result
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

  val lists = Solution102().levelOrder(node1)
  println(lists)
}