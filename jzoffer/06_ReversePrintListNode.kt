package jzoffer

import ListNode
import java.util.Stack

val stack = Stack<Int>()

fun reversePrint(head: ListNode?): IntArray {

  var tempNode = head
  while (tempNode != null) {
    stack.push(tempNode.`val`)
    tempNode = if (tempNode.next != null) {
      tempNode.next!!
    } else {
      null
    }
  }
  val result = IntArray(stack.size)
  var index = 0

  while (stack.isNotEmpty()) {
    result[index] = stack.pop()
    index++
  }
  return result
}


fun reversePrint2(head: ListNode?) {

  if (head != null) {
    reversePrint2(head.next)
  }
  if (head != null) {
    println(head.`val`)
  }
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

  val reversePrint = reversePrint(node1)
  // reversePrint.forEach {
  //   println(it)
  // }
  reversePrint2(node1)
}