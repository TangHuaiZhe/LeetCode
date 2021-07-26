package jzoffer

fun numWays(n: Int): Int {

  if (n == 0 || n == 1) {
    return 1
  }

  val dp = IntArray(n + 1)
  dp[0] = 1
  dp[1] = 1
  for (index in 2..n) {
    dp[index] = (dp[index - 1] + dp[index - 2]) % 1000000007
  }
  return dp[n]
}

fun main() {
  println(numWays(2))
  println(numWays(3))
  println(numWays(10))
  println(numWays(14))
}