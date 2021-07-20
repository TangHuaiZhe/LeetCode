package jzoffer

import java.lang.StringBuilder

/**
 * java由于string是不可变类型，因此必须要一个额外的O(n)空间，
 * 如果使用C++实现，可以在原来的String上修改！
 */
fun replaceSpace(s: String): String {
  val res = StringBuilder();
  for (c in s.toCharArray()) {
    if (c == ' ') res.append("%20");
    else res.append(c);
  }
  return res.toString();
}

fun main() {
  println("We are happy.".length)
  val replaceSpace = replaceSpace("We are happy.")
  println(replaceSpace)
}