import chapter14._

val op1: Option[Parent] = Option(new Child(1))
val p1: Parent = op1.getOrElse(new Parent(10))

