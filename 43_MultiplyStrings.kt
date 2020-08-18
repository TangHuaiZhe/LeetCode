import java.util.Arrays

/**
 * author: tang
 * created on: 2019-08-23 12:30
 * description:
 */

// 给定两个以字符串形式表示的非负整数 num1 和 num2，
// 返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
//
//示例 1:
//
//输入: num1 = "2", num2 = "3"
//输出: "6"
//示例 2:
//
//输入: num1 = "123", num2 = "456"
//输出: "56088"

//说明：
//
//num1 和 num2 的长度小于110。
//num1 和 num2 只包含数字 0-9。
//num1 和 num2 均不以零开头，除非是数字 0 本身。
//不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

fun multiply(num1: String, num2: String): String {
  /**
   * num1的第i位(高位从0开始)和num2的第j位相乘的结果在乘积中的位置是[i+j, i+j+1]
   * 例: 123 * 45,
   * 123的第1位 = 2
   * 45的第0位 = 4
   * 乘积 0 8 存放在结果的第[1, 2]位中[i+j, i+j+1]

  0 1 2 3 4
   *
   *   1 2 3
   *     4 5
   * ---------
   *     1 5
   *   1 0
  0 5
   * ---------
   * 0 6 1 5
   *   1 2
   * 0 8
  0 4
   * ---------
  0 5 5 3 5
   * 这样我们就可以单独都对每一位进行相乘计算把结果存入相应的index中
   */
  if (num1 == "0" || num2 == "0") {
    return "0"
  }

  val n1 = num1.length - 1
  val n2 = num2.length - 1
  if (n1 < 0 || n2 < 0) return ""
  val mul = IntArray(n1 + n2 + 2)
  val sb = StringBuilder()
  for (i in n1 downTo 0) {
    for (j in n2 downTo 0) {
      var bitmul = (num1[i] - '0') * (num2[j] - '0')
      // 结果存到数组的两个相邻元素 [i+j:i+j+1] 前面是进位 后面是余数

      // 先加上次存储的结果
      bitmul += mul[i + j + 1]

      // 高位是否有进位，有则加到前面
      mul[i + j] += bitmul / 10

      // 低位
      mul[i + j + 1] = bitmul % 10
    }
  }
  Arrays.toString(mul).filter { it.isDigit() }.forEach {
    sb.append(it)
  }
  var result = sb.toString()
  while (result.startsWith("0")) {
    result = result.substring(1)
  }
  return result
}

fun main() {
  val num1 = "0"
  val num2 = "0"

  val result = multiply(num1, num2)
  println(result)
}

