package codility

/**
 * description:
 */

fun solution(N: Int): Int {
  // write your code in Kotlin
  val binary = decToBinary(N)
  return scanBinaryGap(binary)
}

fun decToBinary(decNum: Int): String {
  var decNumLocal = decNum
  val result = StringBuilder()
  while (decNumLocal > 0) {
    val r = decNumLocal % 2
    decNumLocal /= 2
    result.append(r)
  }
  return result.reverse().toString()
}

fun scanBinaryGap(binaryString: String):Int {
  var left = 0
  var max = 0
  binaryString.forEachIndexed { index, c ->
    if (c == '1') {
      val gap = index - left - 1
      if (gap > max && gap >= 1) {
        max = gap
      }
      left = index
    }
  }
  return max
}

fun main() {
  val result = solution(15)
  val result1 = solution(1041)
  println(result)
  println(result1)
}
