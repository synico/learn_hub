
object Combinators1 {
  def map[B,A](f: A => B)(list: List[A]): List[B] = list map f
}

val intToString = (i:Int) => s"N=$i"
val flist = Combinators1.map(intToString) _
val list = flist(List(1,2,3,4))
