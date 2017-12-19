package pis.chapter12 {

  abstract class IntQueue {
    def get(): Int
    def put(x: Int)
  }

  import scala.collection.mutable.ArrayBuffer
  class BasicIntQueue extends IntQueue {
    private val buf = new ArrayBuffer[Int]
    def get() = {
      println("BasicIntQueue.get()")
      buf.remove(0)
    }
    def put(x: Int) = {
      println("BasicIntQueue.put(x)")
      buf += x
    }
  }

  trait Doubling extends IntQueue {
    abstract override def put(x: Int) = {
      println("Doubling.put(x) calls super.put(2 * x)")
      super.put(2 * x)
    }
  }

  trait Incrementing extends IntQueue {
    abstract override def put(x: Int) = {
      println("Incrementing.put(x) calls super.put(2 * x)")
      super.put(x + 1)
    }
  }

  trait Filtering extends IntQueue {
    abstract override def put(x: Int) = {
      println("Filtering.put(x) calls super.put(x)")
      if(x > 0) super.put(x)
    }
  }

}
