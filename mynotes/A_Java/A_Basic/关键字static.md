# 关键字static

### static修饰的成员方法或成员变量独立于该类，不依赖该类的任何实例，被所有该类的对象共享。

#### static方法
静态方法可以直接通过类名调用，任何实例方法都可以调用。

1. static方法中不能出现this或super。
2. static方法中不能调用实例变量和实例方法。因为static方法被所有实例共享，不与任何实例关联。
3. static方法不能是abstract的。

#### static变量
在内存中只有一个拷贝，JVM只为static变量分配一次内存，在加载类的过程中完成静态变量的内存分配。

#### static代码块
static代码块可以有多个，位置可以出现在类的任何位置，但是不能在任何方法内。JVM加载类的时候会首先加载类中的static代码块，并按照static代码块出现的顺序执行它们，每个代码块只会被执行一次。

#### static关键字用于以下位置
1. 全局变量声明
2. 静态块声明
3. 方法声明
