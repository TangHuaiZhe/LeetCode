import kotlin.math.abs

/**
 * author: tang
 * created on: 2019-08-22 16:10
 * description:
 */

fun reverse(x: Int): Int {
  val sign = if (x < 0) -1 else 1

  var x1 = abs(x)
  var ans = 0


  while (x1 != 0) {
    // x1 取余数
    ans = ans * 10 + x1 % 10
    println("ans is $ans")
    // 除10，拿到每位的数字
    x1 /= 10
    println(x1)
    if (ans > Int.MAX_VALUE / 10 || (ans == Int.MAX_VALUE / 10 && x1 > 7)) {
      println("bigger than Int.MAX_VALUE")
      return 0
    } else if ((ans * sign < Int.MIN_VALUE / 10 && x1 != 0) || (ans * sign == Int.MIN_VALUE / 10 && x1 < -8)) {
      println("litter than Int.MIN_VALUE")
//        -2,147,483,648  -2,143,847,412
      return 0
    }
  }
  return ans * sign
}

fun main() {
//  Int.MAX_VALUE = 2,147,483,647   Min = -2147483648
  val reverse = reverse(-2147483412)
  println(reverse)
}