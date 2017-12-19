import chapter14._

case class Opt[+A](value: A = null) {
  def getOrElse[B >: A](default: => B) = if (value != null) value else default
}

//val op3: Opt[Parent] = Opt[Child](null)
//val p3a: Parent = op3.getOrElse(new Parent(20))
//val p3b: Parent = op3.getOrElse(new Child2(11))

val op4: Opt[Child] = Opt[Child](null)
val p4a: Child = op4.getOrElse(new Child(1))