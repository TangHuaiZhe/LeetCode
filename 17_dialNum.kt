import java.util.ArrayList
import java.util.HashMap

/**
 * author: tang
 * created on: 2019-05-20 17:30
 * description:
 * 这道题本来相同循环去做，但是发现循环的个数不固定，
 * 因此这种情况得用递归，使用BFS+hash table完成
 */
class Solution {
  fun letterCombinations(digits: String?): List<String> {
    if (digits == null || digits.isEmpty()) {
      return ArrayList()
    }
    val map = HashMap<Char, String>()
    map['2'] = "abc"
    map['3'] = "def"
    map['4'] = "ghi"
    map['5'] = "jkl"
    map['6'] = "mno"
    map['7'] = "pqrs"
    map['8'] = "tuv"
    map['9'] = "wxyz"
    val res = mutableListOf<String>()
    helper(s = "", digits = digits, i = 0, res = res, map = map)
    return res
  }

  private fun helper(
    s: String,
    digits: String,
    i: Int,
    res: MutableList<String>,
    map: Map<Char, String>
  ) {
    //递归的边界条件
    if (i == digits.length) {
      res.add(s)
      return
    }
    val letters = map[digits[i]]
    if (letters != null) {
      for (j in 0 until letters.length) {
        println("j is $j and i+1 is ${(i + 1)} and letters is $letters")
        helper(s = s + letters[j], digits = digits, i = i + 1, res = res, map = map)
        println("res is $res")
      }
    }

  }
}

fun main() {
  val result = Solution().letterCombinations("234")
  print(result)
}