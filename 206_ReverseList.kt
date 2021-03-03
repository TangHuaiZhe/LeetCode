//反转一个单链表。
//
// 示例:
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL
//
// 进阶:
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
// Related Topics 链表
// 👍 1544 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
fun reverseList(head: ListNode?): ListNode? {
  if (head?.next == null) return head
  val last = reverseList(head.next!!)
  head.next!!.next = head
  head.next = null
  return last
}

fun main() {
  val node5 = ListNode(5)
  val node4 = ListNode(4)
  val node3 = ListNode(3)
  val node2 = ListNode(2)
  val node1 = ListNode(1)

  node1.next = node2
  node2.next = node3
  node3.next = node4
  node4.next = node5

  println(reverseList(node1))

}
//leetcode submit region end(Prohibit modification and deletion)
