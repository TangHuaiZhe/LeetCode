/**
 * author: tang
 * created on: 2019-11-21 15:09
 * description:翻转链表
 */
class Node(private val value: Int) {
  var next: Node? = null
  override fun toString(): String {
    return "Node(value=$value, next=$next)"
  }

  companion object {

    fun reverseList(current: Node?): Node? {
      var cur = current
      var pre: Node? = null
      var next: Node?

      // cur第一次是Head结点
      while (cur != null) {
        //下一个结点
        next = cur.next

        //翻转结点关联
        cur.next = pre

        // 更新pre
        pre = cur
        // 更新cur
        cur = next
      }
      return pre
    }
  }
}

fun main() {
  val node5 = Node(5)
  val node4 = Node(4)
  val node3 = Node(3)
  val node2 = Node(2)
  val node1 = Node(1)

  node1.next = node2
  node2.next = node3
  node3.next = node4
  node4.next = node5

  println(node1)

  println(Node.reverseList(node1))
}




