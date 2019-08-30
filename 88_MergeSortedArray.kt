package leetcode

/**
 * author: tang
 * created on: 2019-08-22 10:49
 * description:
 */

//给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
//
//说明:
//
//初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
//你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
//示例:
//
//输入:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//输出: [1,2,2,3,5,6]

//直觉
//
//一般而言，对于有序数组可以通过 双指针法 达到O(n + m)O(n+m)的时间复杂度。
//
//最直接的算法实现是将指针p1 置为 nums1的开头， p2为 nums2的开头，在每一步将最小值放入输出数组中。
//
//由于 nums1 是用于输出的数组，需要将nums1中的前m个元素放在其他地方，也就需要 O(m)O(m) 的空间复杂度。

fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
  // Make a copy of nums1.
  val nums1Copy = IntArray(m)
  System.arraycopy(nums1, 0, nums1Copy, 0, m)

  // Two get pointers for nums1_copy and nums2.
  var p1 = 0
  var p2 = 0

  // Set pointer for nums1
  var p = 0

  // Compare elements from nums1_copy and nums2
  // and add the smallest one into nums1.
  while ((p1 < m) && (p2 < n))
    nums1[p++] = if (nums1Copy[p1] < nums2[p2]) nums1Copy[p1++] else nums2[p2++];

  // if there are still elements to add
  if (p1 < m)
    System.arraycopy(nums1Copy, p1, nums1, p1 + p2, m + n - p1 - p2);
  if (p2 < n)
    System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
}

fun main() {

}
