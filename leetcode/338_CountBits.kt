import java.lang.StringBuilder

//给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
//
// 示例 1:
//
// 输入: 2
//输出: [0,1,1]
//
// 示例 2:
//
// 输入: 5
//输出: [0,1,1,2,1,2]
//
// 进阶:
//
//
// 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
// 要求算法的空间复杂度为O(n)。
// 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
//
// Related Topics 位运算 动态规划
// 👍 773 👎 0

internal class Solution338 {
  fun countBits(n: Int): IntArray {
    val result = IntArray(n + 1)
    for (i in 0..n) {
      result[i] = toBinary(i).count { it == '1' }
    }
    return result
  }

  fun toBinary(n: Int): String {
    val res = StringBuilder()
    var num = n
    while (num > 0) {
      res.append(num % 2)
      num = num shr 1
    }
    return res.reverse().toString()
  }

  fun countBits2(n: Int): IntArray {
    val result = IntArray(n + 1)
    for (i in 0..n) {
      result[i] = count(i)
    }
    return result
  }

  private fun count(num: Int): Int {
    if (num == 0) {
      return 0
    }

    // 如果 i 是奇数，那么它的二进制 1 的位数 = i-1的二进制1位数 + 1
    // 因为奇数的二进制末尾==1，如果把末尾去掉就是i-1。
    // 又因为i-1是偶数，所以奇数i的二进制1的个数 == i/2中二进制1的位数+1
    if (num % 2 == 1) {
      return count(num - 1) + 1
    }
    // num 是偶数，二进制 1的位数== num/2
    // 因为偶数的二进制末尾是 0，右移一位等于i/2，而二进制中 1 的个数没有变化
    return count(num / 2)
  }


  fun countBits3(n: Int): IntArray {
    val result = IntArray(n + 1)
    for (i in 1..n) {
      // 如果 i 是奇数，那么它的二进制 1 的位数 = i-1的二进制1位数 + 1
      // 因为奇数的二进制末尾==1，如果把末尾去掉就是i-1。
      // 又因为i-1是偶数，所以奇数i的二进制1的个数 == i/2中二进制1的位数+1

      // num 是偶数，二进制 1的位数== num/2
      // 因为偶数的二进制末尾是 0，右移一位等于i/2，而二进制中 1 的个数没有变化
      result[i] = result[i shr 1] + (i and 1)
    }
    return result
  }
}

fun main() {
  println(Solution338().toBinary(3))
  println(Solution338().toBinary(5))
  println(Solution338().toBinary(8))

  println(Solution338().countBits(3).contentToString())
  println(Solution338().countBits(5).contentToString())
  println(Solution338().countBits(8).contentToString())


  println(Solution338().countBits2(3).contentToString())
  println(Solution338().countBits2(5).contentToString())
  println(Solution338().countBits2(8).contentToString())

  println(Solution338().countBits3(3).contentToString())
  println(Solution338().countBits3(5).contentToString())
  println(Solution338().countBits3(8).contentToString())

}