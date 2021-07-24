package jzoffer

import java.util.Stack

/**
用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead
分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead操作返回 -1 )

 

示例 1：

输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]
示例 2：

输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
提示：

1 <= values <= 10000
 **/

class CQueue() {

  private val stack1 = Stack<Int>()
  private val stack2 = Stack<Int>()

  fun appendTail(value: Int) {
    stack1.push(value)
  }

  fun deleteHead(): Int {
    if (stack2.isEmpty()) {
      while (stack1.isNotEmpty()) {
        stack2.push(stack1.pop())
      }
    }
    return if (stack2.isEmpty()) {
      -1
    } else {
      stack2.pop()
    }
  }
}

fun main() {
  val cQueue = CQueue()
  cQueue.appendTail(3)
  cQueue.appendTail(2)
  cQueue.appendTail(1)
  println(cQueue.deleteHead())
  println(cQueue.deleteHead())
  cQueue.appendTail(4)
  println(cQueue.deleteHead())
  println(cQueue.deleteHead())
  println(cQueue.deleteHead())
}

/**
 * Your CQueue object will be instantiated and called as such:
 * var obj = CQueue()
 * obj.appendTail(value)
 * var param_2 = obj.deleteHead()
 */