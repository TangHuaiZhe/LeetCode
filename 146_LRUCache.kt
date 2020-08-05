import java.util.LinkedList

//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
//
// 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
//写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在
//写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
//
//
//
// 进阶:
//
// 你是否可以在 O(1) 时间复杂度内完成这两种操作？
//
//
//
// 示例:
//
// LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // 返回  1
//cache.put(3, 3);    // 该操作会使得关键字 2 作废
//cache.get(2);       // 返回 -1 (未找到)
//cache.put(4, 4);    // 该操作会使得关键字 1 作废
//cache.get(1);       // 返回 -1 (未找到)
//cache.get(3);       // 返回  3
//cache.get(4);       // 返回  4
//
// Related Topics 设计
// 👍 797 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache(val capacity: Int) {

  var list: LinkedList<Int> = LinkedList()

  init {
    list.set(0,0)
  }

  fun get(key: Int): Int {
    return if (list[key] != 0) {
      // update
      val result = list[key]
      list[capacity-1] = result
      result
    } else {
      -1
    }
  }

  fun put(key: Int, value: Int) {
    if (list.size == capacity ){
      list[key] = value
      list[capacity-1] = list[capacity-2]
    } else {
      list[key] = value
    }
  }

}

fun main() {
  var obj = LRUCache(100)
  obj.put(1,1)
  obj.put(2,2)
  println(obj.get(2))
//  obj.put(key,value)
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */
//leetcode submit region end(Prohibit modification and deletion)
