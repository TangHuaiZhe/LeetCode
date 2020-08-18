/**
 * author: tang
 * created on: 2019-08-22 10:08
 * description:
 */

// 将两个有序链表合并为一个新的有序链表并返回。
// 新链表是通过拼接给定的两个链表的所有节点组成的。 
//
//示例：
//
//输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4

//方法 1：递归
//想法
//我们可以如下递归地定义在两个链表里的 merge 操作（忽略边界情况，比如空链表等）：
//    list1[0]<list2[0]:
//    list1[0]+merge(list1[1:],list2)
//
//    otherwise:
//    list2[0]+merge(list1,list2[1:])
//    ​
//    也就是说，两个链表头部较小的一个与剩下元素的 merge 操作结果合并。
//
//    算法
//    我们直接将以上递归过程建模，首先考虑边界情况。
//    特殊的，如果 l1 或者 l2 一开始就是 null ，那么没有任何操作需要合并，
//    所以我们只需要返回非空链表。否则，我们要判断 l1 和 l2 哪一个的头元素更小，
//    然后递归地决定下一个添加到结果里的值。
//    如果两个链表都是空的，那么过程终止，所以递归过程最终一定会终止。

fun main() {
  val node4 = ListNode(4)
  val node2 = ListNode(2)
  val node1 = ListNode(1)
  node1.next = node2
  node2.next = node4

  val node44 = ListNode(4)
  val node33 = ListNode(3)
  val node11 = ListNode(1)
  node11.next = node33
  node33.next = node44

  val twoLists = mergeTwoLists(node1, node11)
  println(twoLists)
}

fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
  return when {
    l1 == null -> l2
    l2 == null -> l1

    l1.`val` < l2.`val` -> {
      l1.next = mergeTwoLists(l1.next, l2)
      l1
    }

    else -> {
      l2.next = mergeTwoLists(l1, l2.next)
      l2
    }
  }
}

fun mergeTwoLists2(l1: ListNode?, l2: ListNode?): ListNode? {
  var l1_ = l1
  var l2_ = l2

  // maintain an unchanging reference to node ahead of the return node.
  val prehead = ListNode(-1);
  var prev = prehead
  while (l1_ != null && l2_ != null) {
    if (l1_.`val` <= l2_.`val`) {
      prev.next = l1_
      l1_ = l1_.next
    } else {
      prev.next = l2_
      l2_ = l2_.next
    }
    prev = prev.next!!
  }
  // exactly one of l1 and l2 can be non-null at this point, so connect
  // the non-null list to the end of the merged list.
  prev.next = l1_ ?: l2_
  return prehead.next;
}
