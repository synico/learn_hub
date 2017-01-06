#集合之Set

####HashSet
1. HashSet由链表数组实现。使用HashMap作为存储载体。
2. 当不关心集合中元素顺序的时候才应该使用。

####TreeSet
1. TreeSet是一个有序集合（Sorted Collection）。使用TreeMap作为存储载体。
2. 将一个元素添加到TreeSet中要比添加到HashSet中要慢，但是比将元素添加到数组或链表的指定位置要快。
3. 实现集合中元素按顺序排列的两种方法。第一，元素实现Comparable接口的compareTo方法。第二，将Comparator对象传递给TreeSet的构造器。

####LinkedHashSet

####EnumSet

####BitSet