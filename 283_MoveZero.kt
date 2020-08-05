//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例:
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0]
//
// 说明:
//
//
// 必须在原数组上操作，不能拷贝额外的数组。
// 尽量减少操作次数。
//
// Related Topics 数组 双指针
// 👍 681 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution283 {
  fun moveZeroes(nums: IntArray): Unit {
    var a = -1
    var b = -1
    nums.forEachIndexed { index, value ->
      if (value == 0) { //value is zero
        if (a == -1) { // no zero found yet
          a = index
        } else { // zero already found
          b = index
        }
      } else { // value not zero
        if (a != -1) { // zero already found
          nums[a] = value
          nums[index] = 0
          a = if (b == -1) { // only one zero found
            index
          } else {//slide a++
            ++a
          }
          b = index
        }
      }
    }
  }


  fun moveZeroes1(nums: IntArray) {
    //第一次遍历的时候，j指针记录非0的个数，只要是非0的统统都赋给nums[j]
    var j = 0
    nums.forEachIndexed { index, value ->
      if(nums[index]!=0) {
        nums[j++] = value
      }
    }

    //非0元素统计完了，剩下的都是0了
    //所以第二次遍历把末尾的元素都赋为0即可
    nums.forEachIndexed { index, value ->
      if (index >= j) {
        nums[index] = 0
      }
    }
  }

  fun moveZeroes2(nums: IntArray) {
    //两个指针i和j
    var j = 0
    for (i in nums.indices) {
      //当前元素!=0，就把其交换到左边，等于0的交换到右边
      if (nums[i] != 0) {
        val tmp = nums[i]
        nums[i] = nums[j]
        nums[j] = tmp
        j++
      }
    }
  }
}


//leetcode submit region end(Prohibit modification and deletion)
fun main() {
  val nums = intArrayOf(4,2,4,0,0,3,0,5,1,0)
  Solution283().moveZeroes2(nums)
  nums.forEach {
    println("$it")
  }
}