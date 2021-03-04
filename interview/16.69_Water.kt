package interview

import java.util.ArrayDeque
import java.util.Deque
import java.util.LinkedList

//你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
//
//示例：
//
//输入：
//[
//[0,2,1,0],
//[0,1,0,1],
//[1,1,0,1],
//[0,1,0,1]
//]
//输出： [1,2,4]
//提示：
//
//0 < len(land) <= 1000
//0 < len(land[i]) <= 1000

//BFS 的几个主要步骤
//
//肯定会用到 deque 的结构用来模拟队列，BFS精髓也在这里。
//队列里肯定是有一个初始点
//然后每次处理从队列中出队一个元素
//对元素进行扩张(具体如何扩张需要根据题目要求，一般是上下左右四个方向，本题是算上斜向共8个方向)
//对于扩张后满足某条件的点再进行处理，根据需要进入队列，进入队列的点就是扩到下一层的点(不同题目需要处理的方法不同，大家灵活运用)
//然后接着循环处理 deque 中的元素，直到 deque 为空，则代表所有点都已经完成扩张
//最后根据题目要求输出结果(当然这已经不属于 BFS 模板的范围了)
//所有 BFS 的模板题都大致是这个思路，只不过是可能有小的变形。
//最重要的还是要培养自己辨识某题是 BFS 的能力和敏感度，还有就是要明确要从那些点开始扩张，搞清楚这两点再加上 BFS 模板，这类题就问题不大了。

//queue=collections.deque([])
//queue.append(第一个满足条件的点)
//
//while queue 不空：
//cur = queue.popleft()
//标记我们访问过cur这个点，从而之后不会再访问到这个点
//for 节点 in cur的所有相邻节点：
//if 该节点有效且满足条件：
//queue.append(该节点)
//标记我们访问过这个节点

//leetcode submit region begin(Prohibit modification and deletion)
fun pondSizes(land: Array<IntArray>): IntArray {
  val result = ArrayList<Int>()
  val visited = HashMap<Pair<Int, Int>, Boolean>()
  land.forEachIndexed { row: Int, ints: IntArray ->
    ints.forEachIndexed { col, value ->
      if (value == 0 &&  visited[Pair(row, col)] != true) {
        val linkedList = LinkedList<Pair<Int, Int>>()
        var tempCount = 1
        visited[Pair(row, col)] = true
        linkedList.add(Pair(row, col))

        while (linkedList.size > 0) {
          val node = linkedList.pollFirst()
          val x = node.first
          val y = node.second
          val adjacent = listOf(Pair(x + 1, y),
              (Pair(x - 1, y)),
              (Pair(x, y + 1)),
              (Pair(x, y - 1)),
              (Pair(x - 1, y - 1)),
              (Pair(x - 1, y + 1)),
              (Pair(x + 1, y + 1)),
              (Pair(x + 1, y - 1))
          )
          for (pair in adjacent) {
            if (land.size > pair.first && pair.first >= 0
                && land[0].size > pair.second && pair.second >= 0
                && land[pair.first][pair.second] == 0
                && visited[Pair(pair.first, pair.second)] != true
            ) {
              tempCount += 1
              visited[Pair(pair.first, pair.second)] = true
              linkedList.add(pair)
            }
          }
        }
        println(linkedList)
        println(tempCount)
        result.add(tempCount)
      }
    }
  }
  result.sort()
  return result.toIntArray()
}


fun pondSizes2(land: Array<IntArray>): IntArray {
  val res = mutableListOf<Int>()
  for (i in land.indices) {
    for (j in land[i].indices) {
      if (land[i][j] == 0) {
        val cur = dfs(land, i, j)
        res.add(cur)
      }
    }
  }
  return res.sorted().toIntArray()
}

fun dfs(land: Array<IntArray>, i: Int, j: Int): Int {
  if (i !in land.indices || j !in land[i].indices || land[i][j] != 0) {
    return 0
  }
  land[i][j] = 1
  return dfs(land, i + 1, j + 1) + dfs(land, i + 1, j - 1) + dfs(land, i - 1, j + 1) + dfs(land, i - 1, j - 1) +
      dfs(land, i + 1, j) + dfs(land, i - 1, j) + dfs(land, i, j + 1) + dfs(land, i, j - 1) + 1
}

fun main() {
  val a = intArrayOf(0, 2, 1, 0)
  val b = intArrayOf(0, 1, 0, 1)
  val c = intArrayOf(1, 1, 0, 1)
  val d = intArrayOf(0, 1, 0, 1)
  val input = arrayOf(a, b, c, d)
  val result = pondSizes(input)
  println("!!!!!!!!!!!!")
  result.forEach {
    println(it)
  }
}