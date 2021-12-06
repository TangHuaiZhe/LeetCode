package jzoffer

class TreeNode(var `val`: Int) {
  var left: TreeNode? = null
  var right: TreeNode? = null
}

val inorderIndexMap: HashMap<Int, Int> = HashMap()

fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
  val n = preorder.size
  for (i in 0 until n) {
    // 键表示一个元素（节点的值），值表示其在中序遍历中的出现位置
    inorderIndexMap[inorder[i]] = i
  }
  return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1)
}

/**
 * 对于任意一颗树而言，前序遍历(先根)的形式总是
[ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ] 即根节点总是前序遍历中的第一个节点。
而中序遍历（中根）的形式总是
[ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
 */
fun myBuildTree(
  preorder: IntArray,
  inorder: IntArray,
  preLeft: Int, // 前序left索引
  preRight: Int,
  inLeft: Int, // 中序left
  inRight: Int
): TreeNode? {
  if (preLeft > preRight) return null

  // 前序遍历中的第一个节点就是根节点 , 本质上就是不断找到子问题（左右子树）的root节点
  val preRoot = preLeft

  // 先把根节点建立出来
  val root = TreeNode(preorder[preRoot])

  // 在中序遍历中定位根节点的位置
  val inRootIndex = inorderIndexMap[preorder[preRoot]]!!

  // 得到中序遍历  左子树中的节点数目
  val leftSubtreeSize: Int = inRootIndex - inLeft

  // 递归地构造左子树，并连接到根节点
  // 关键是理解 递归， 大问题分解为重复的小问题，分治的思想
  // 第一次处理的是如下两个前序中序数组，第二次处理的是什么？递归的是找到的那颗左子树，然后不断的递归，最后递归右子树
  //  前序遍历结果: [ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
  //  中序遍历结果:  [ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
  //  处理[左子树的中序遍历结果] 和处理 中序遍历结果的方式是一样的， 因此 就是递归
  // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素 ,
  // +1是因为前序遍历第preLeft个元素是第一次的根root
  root.left =
    myBuildTree(preorder, inorder, preLeft + 1, preLeft + leftSubtreeSize, inLeft, inRootIndex - 1)

  // 递归地构造右子树，并连接到根节点
  // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
  root.right =
    myBuildTree(
      preorder, inorder, preLeft + 1 + leftSubtreeSize, preRight, inRootIndex + 1, inRight
    )

  return root
}

fun main() {
  val preorder = intArrayOf(3, 9, 20, 15, 7)
  val inorder = intArrayOf(9, 3, 15, 20, 7)
  val buildTree = buildTree(preorder, inorder)
  println(buildTree)
}
