import java.util.Stack

/**
 * author: tang
 * created on: 2019-08-21 17:08
 * description:
 */

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
//有效字符串需满足：
//
//左括号必须用相同类型的右括号闭合。
//左括号必须以正确的顺序闭合。
//注意空字符串可被认为是有效字符串。
//
//示例 1:
//
//输入: "()"
//输出: true
//示例 2:
//
//输入: "()[]{}"
//输出: true
//示例 3:
//
//输入: "(]"
//输出: false
//示例 4:
//
//输入: "([)]"
//输出: false
//示例 5:
//
//输入: "{[]}"
//输出: true
fun main() {
  println(isValid("()[]{}"))
  println(isValid("([{()}])"))
  println(isValid("([{()}]){}()"))
  println(isValid("["))
}

fun isValid(string: String): Boolean {

  if (string.isEmpty()) {
    return true
  }

  val stack: Stack<Char> = Stack()
  string.forEach {
    if (it in arrayListOf('(', '{', '[')) {
      stack.push(it)
    } else {
      if (stack.isEmpty()) {
        return false
      }
      if (it == ')' && stack.peek() != '(') {
        return false
      }
      if (it == '}' && stack.peek() != '{') {
        return false
      }
      if (it == ']' && stack.peek() != '[') {
        return false
      }
      stack.pop()
    }
  }
//  println(stack)
  return stack.isEmpty()
}