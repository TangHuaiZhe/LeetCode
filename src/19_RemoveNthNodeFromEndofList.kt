/**
 * author: tang
 * created on: 2019-08-21 13:11
 * description:
 */

//给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
//
//示例：
//
//给定一个链表: 1->2->3->4->5, 和 n = 2.
//
//当删除了倒数第二个节点后，链表变为 1->2->3->5.
//说明：
//
//给定的 n 保证是有效的。
//
//进阶：
//
//你能尝试使用一趟扫描实现吗？

//采取双重遍历肯定是可以解决问题的，但题目要求我们一次遍历解决问题，那我们的思路得发散一下。
//
// 我们可以设想假设设定了双指针 p 和 q 的话，当 q 指向末尾的 NULL，
// p 与 q 之间相隔的元素个数为 n 时，那么删除掉 p 的下一个指针就完成了要求。
//
//设置虚拟节点 dummyHead 指向 head
//设定双指针 p 和 q，初始都指向虚拟节点 dummyHead
//移动 q，直到 p 与 q 之间相隔的元素个数为 n
//同时移动 p 与 q，直到 q 指向的为 NULL
//将 p 的下一个节点指向下下个节点

class ListNode(var `val`: Int) {
  var next: ListNode? = null
  override fun toString(): String {
    return "ListNode(`val`=$`val`, next=$next)"
  }
}

fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
  val dummy = ListNode(0)
  dummy.next = head
  var first = dummy as ListNode?
  var second = dummy
  // Advances first pointer so that the gap between first and second is n nodes apart
  for (i in 0..n) {
    println(i)
    first = first?.next
    println(first)
  }
  // Move first to the end, maintaining the gap
  while (first != null) {
    first = first.next
    second = second.next!!
  }
  second.next = second.next?.next
  return dummy.next
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

  println(removeNthFromEnd(node1, 2))

  val node11 = ListNode(1)
  println(removeNthFromEnd(node11, 1))

}