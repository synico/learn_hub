# 集合之Queue

#### ArrayDeque
**数据结构**：由循环数组实现，两个数组下标（head, tail）保存数组头尾信息。数组头尾都可以添加删除元素，但是不能在队列中间添加元素。  
**元素类型**：不允许添加null到队列中，否则抛出NPE。  
1. 需要实现FILO时，ArrayDeque比使用Stack快。
2. 需要实现FIFO时，ArrayDeque比使用LinkedList快。因为使用LinkedList，在向其中添加元素时，需要创建更多的对象来完成存储。在GC运行时，需要更多的时间回收更多的空间。

#### PriorityQueue
**数据结构**：由高效的数据结构堆（可自我调整的二叉树）实现。  
**元素类型**：不允许添加null到队列中，否则抛出NPE。  
1. 元素按任意顺序插入，但总是按照排序的顺序检索。
2. 既可以保存实现了Comparable接口的对象，也可以保存利用构造器提供Comparator的对象。

#### ConcurrentLinkedQueue
基于链接节点的线程安全但无阻塞的无限队列，按照先进先出FIFO对元素进行排序。入列时，元素插入到队列的尾部；出列时，从队列的头部获取元素。
1. 其迭代器仅提供弱一致性，仅反映某一时刻或迭代器创建时队列的状态。
2. size方法时间复杂度为O(n)，而不像一般的容器为O(1)。该方法会遍历Queue中所有元素。并且结果也不一定准确。
3. 所有批处理操作如addAll方法，equals方法和toArray方法都不保证原子性。
4. 通过sun.misc.Unsafe的Unsafe机制实现无阻塞的线程安全。

#### BlockingQueue方法
操作|抛出异常|特殊值|阻塞|超时
:--|:--|:--|:--|:--
插入|add(e)|offer(e)|put(e)|offer(e, time, unit)
移除|remove()|poll()|take()|poll(time, unit)
检查|element()|peek()|无|无

#### ArrayBlockingQueue
以数组作为存储结构的FIFO有界阻塞队列，使用ReentrantLock锁实现线程安全。
1. 新加入的元素存于队列的尾部，从队列的头部检出元素。
2. 队列的大小在创建时指定，之后无法更改。
3. 试图将元素加入（put方法）已满的队列，或试图从空队列中检出（take方法）元素都会导致阻塞。
4. 支持公平锁和非公平锁。

#### LinkedBlockingQueue
以链表作为存储结构的FIFO可选边界阻塞队列，使用ReentrantLock锁实现线程安全。
1. 新加入的元素存于队列的尾部，从队列的头部检出元素。
2. 队列的大小可在Queue创建时指定，也可以不指定（无界队列）。
3. 如果是有界队列，试图将元素加入（put方法）已满的队列，或试图从空队列中检出（take方法）元素都会导致阻塞。
4. 链表队列的吞吐量通常高于数组队列，但是在并发情况下，链表队列可预测的性能不如数组队列。

#### SynchronousQueue

#### DelayQueue

#### LinkedBlockingDeque

#### PriorityBlockingQueue
