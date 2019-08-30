package leetcode

/**
 * author: tang
 * created on: 2019-08-23 10:01
 * description:
 */

//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
//如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//
//您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//示例：
//
//输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {

  var l1Temp = l1
  var l2Temp = l2

  // 操作链表,搞个head方便许多
  val head = ListNode(0)

  var currentNode = head

  // 进位
  var carry = 0


  while (l1Temp != null || l2Temp != null) {
    val x = l1Temp?.`val` ?: 0
    val y = l2Temp?.`val` ?: 0

    val sum = x + y + carry

    carry = sum.div(10)

    //输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    //输出：7 -> 0 -> 8
    currentNode.next = ListNode(sum % 10)
    currentNode = currentNode.next!!
    l1Temp = l1Temp?.next
    l2Temp = l2Temp?.next
  }
  if (carry > 0) {
    currentNode.next = ListNode(carry)
  }
  return head.next
}

fun main() {
  val node3 = ListNode(3)
  val node4 = ListNode(3)
  val node2 = ListNode(2)

  node2.next = node4
  node4.next = node3

  val node44 = ListNode(4)
  val node66 = ListNode(6)
  val node55 = ListNode(5)

  node55.next = node66
  node66.next = node44


  println(addTwoNumbers(node2, node55))

}