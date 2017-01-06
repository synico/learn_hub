#关键字synchronized

####synchronized方法
1. synchronized修饰的实例方法获得的是对象的内部锁。synchronized修饰的静态方法获得的是类对象的内部锁。
2. 当某个线程获得对象锁（类对象锁）时，其他并发访问持有相同锁（同一个对象的对象锁）的方法将被阻塞。不持有锁或持有不同锁的方法不受影响。

####synchronized块
1. synchronized块可以通过类名.class获得类对象的内部锁。
2. synchronized块可以通过this来获得对象锁。
3. synchronized块可以使用额外对象的锁实现额外的原子操作。但是这种锁不稳定，除非这个额外对象是不可变的对象或者额外对象的所有可修改方法都是同步的。

####synchronized和ReentrantLock
1. synchronized方法或块中不能中断另外一个正在等待（已阻塞）的线程，但是ReentranetLock可以通过lockInterruptibly方法实现。
2. synchronized方法或块中无法通过轮循的方式获得锁，但是ReentranetLock可以通过tryLock方法实现。
3. synchronized方法或块中仅有一个内部条件，但是ReentrantLock可以使用Condition实现多个条件变量。

####Object中与多线程相关的方法
wait方法仅能在synchronized方法中调用，notify和notifyAll方法仅能在synchronized方法或块中调用，如果当前线程不是对象锁的持有者，则会抛出异常。

1. wait方法：导致调用线程进入等待状态（Thread.State.WAITING）。仅能通过其他线程调用notify或notifyAll方法，重新通过竞争获得锁，如未获得锁则继续等待。
2. notify方法：随机选择一个在该对象上调用wait的线程，解除其阻塞状态。
3. notifyAll方法：解除所有在该对象上调用wait方法线程的阻塞状态。