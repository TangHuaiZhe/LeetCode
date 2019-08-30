package leetcode

/**
 * author: tang
 * created on: 2019-08-23 10:54
 * description:
 */

//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
//
//注意：
//num1 和num2 的长度都小于 5100.
//num1 和num2 都只包含数字 0-9.
//num1 和num2 都不包含任何前导零。
//你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。

fun addStrings(num1: String, num2: String): String {
  //双指针
  var index1 = num1.length - 1
  var index2 = num2.length - 1

  //进位
  var carry = 0

  val result = StringBuilder()

  //反过来遍历
  while (index1 >= 0 || index2 >= 0) {

    //char转int
    val x = if (index1 >= 0) num1[index1] - '0' else 0
    val y = if (index2 >= 0) num2[index2] - '0' else 0

    val sum =
        x + y + carry

    carry = sum.div(10)

    //总是插在最前
    result.insert(0, sum % 10)

    index1 -= 1
    index2 -= 1
  }
  // 如果还有进位
  if (carry > 0) {
    result.insert(0, carry)
  }
  return result.toString()
}

fun main() {
  val num1 = "9"
  val num2 = "99"

  val addStrings = addStrings(num1, num2)
  println(addStrings)
}