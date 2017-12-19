import pis.chapter12._

import scala.annotation.tailrec

val myQueue = new BasicIntQueue with Doubling with Filtering
myQueue.put(1)
println(myQueue.get)

val v_list = List[Int](1, 2, 3, 4)

@tailrec
def func[T](list: List[T]): List[T] = list match {
  case head +: tail => {
    println(s"head: $head")
    func(tail)
  }
  case _ => List.empty
}

var func2 = (list: List[Int]) => list match {
  case head +: tail => {
    println(s"func2::head = $head")
    tail
  }
  case Nil => Nil
}

func(v_list)
func2(v_list)