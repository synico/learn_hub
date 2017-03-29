# 集合之Map

#### HashMap
1. HashMap用链表数组实现，每个链表被称为桶（bucket）。要查找表中元素的位置，先通过元素的hashcode方法计算出它的hashcode，然后与桶的总数取余，所得到的结果就是这个元素所在桶的索引（hash算法）。
2. 如果HashMap太满（当前load factor大于预设值），就需要对HashMap执行rehash操作。rehash的过程是创建一个新的HashMap，并将原Map中所有元素添加到新Map中，然后丢弃原Map。
3. Map中键值K是唯一的，如果对同一个键值两次调用put方法，第二个加入的值V会覆盖第一个值。
4. 要同时查看键与值，可以通过枚举各个条目（Map.Entry）查看，避免对值进行查找。

#### WeakHashMap
只有当值（value）的生命周期是由该键（key）的外部引用而不是由值决定时，WeakHashMap才有用处。
1. 如果有一个值，对应的键已经不再使用。因为GC只跟踪活动的对象，只要Map是活动的，其中所有的桶也是活动的，那么它们就不能被回收。这种情况下使用WeakHashMap来完成回收。
2. 使用弱引用（weak reference）保存键。WeakReference对象将引用保存到另外一个对象（散列表键）中。对于这种类型的对象，GC使用一种特有的方式进行处理。

#### TreeMap
基于红黑树的可排序Map实现。

#### LinkedHashMap
与HashMap的区别在于，它维护着一个运行于所有元素的双向链表，用来保存元素将键插入到Map的顺序。可通过迭代器按插入顺序遍历元素。

#### IdentityHashMap
1. 键的hashcode不是由元素的hashcode函数计算，而是由System.identityHashCode方法计算。这是Object.hashCode方法根据对象的内存地址来计算散列码时所用的方式。
2. 在两个对象比较时，IdentityHashMap使用==，而不是equals。

#### ConcurrentHashMap

#### ConcurrentSkipListMap
