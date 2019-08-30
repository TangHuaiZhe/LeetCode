package leetcode

import kotlin.math.min

/**
 * author: tang
 * created on: 2019-08-22 14:53
 * description:
 */

//给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
//
//请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
//
//你可以假设 nums1 和 nums2 不会同时为空。
//
//示例 1:
//
//nums1 = [1, 3]
//nums2 = [2]
//
//则中位数是 2.0
//示例 2:
//
//nums1 = [1, 2]
//nums2 = [3, 4]
//
//则中位数是 (2 + 3)/2 = 2.5

// 两个有序数组求中位数，问题一般化为，求两个有序数组的第k个数，当k = (m+n)/2时为原问题的解。
// 怎么求第k个数？分别求出第一个和第二个数组的第 k / 2个数 a 和 b，
// 然后比较 a 和 b，当a < b ，
// 说明第 k 个数位于 a数组的第 k / 2个数后半段，
// 或者b数组的 第 k / 2 个数前半段，问题规模缩小了一半，然后递归处理就行。
// 时间复杂度是 O(log(m+n))

//时间复杂度: 题目的要求 O(log(m+n)O(log(m+n)。
//看到 O(log)，很明显，我们只有用到二分的方法才能达到。
//我们不妨用另一种思路，题目是求中位数，其实就是求第 k 小数的一种特殊情况，而求第 k 小数有一种算法

//由于数列是有序的，其实我们完全可以一半儿一半儿的排除。假设我们要找第 k 小数，我们可以每次循环排除掉 k/2 个数

//一般的情况,两个有序数组: A[1] ，A[2] ，A[3]，A[k/2] ... ，B[1]，B[2]，B[3]，B[k/2] ...
//如果 A[k/2]<B[k/2] ，那么A[1]，A[2]，A[3]，A[k/2]都不可能是第 k 小的数字。

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
  val n = nums1.size
  val m = nums2.size
  val left = (n + m + 1) / 2
  val right = (n + m + 2) / 2

  //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
  return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left)
      + getKth(nums1, 0, n - 1, nums2, 0, m - 1,
      right)) * 0.5
}

//求有序数组nums1和nums2的第k小数
fun getKth(
  nums1: IntArray,
  start1: Int,
  end1: Int,
  nums2: IntArray,
  start2: Int,
  end2: Int,
  k: Int /* 第k小*/
): Int {

  val len1 = end1 - start1 + 1
  val len2 = end2 - start2 + 1
  //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1 
  if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k)
  if (len1 == 0) return nums2[start2 + k - 1]

  // k == 1时，直接取两边元素比较小的那个就ok
  if (k == 1) return min(nums1[start1], nums2[start2])

  // 需要比较的元素 A[k/2] 和 B[k/2]
  val i = start1 + min(len1, k / 2) - 1
  val j = start2 + min(len2, k / 2) - 1

  // 元素小的一方需要丢弃 k/2 之前的元素
  return if (nums1[i] > nums2[j]) {
    getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1))
  } else {
    getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1))
  }
}

fun main() {
  val nums1: IntArray = intArrayOf(3,4,5,7)
  val nums2: IntArray = intArrayOf(8,9,20,21)
  val findMedianSortedArrays = findMedianSortedArrays(nums2, nums1)
  println(findMedianSortedArrays)
}
