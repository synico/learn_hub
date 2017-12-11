class Parent(val value: Int) {
  override def toString: String = s"${this.getClass.getName}($value)"
}

class Child(value: Int) extends Parent(value = 0)

val op1: Option[Parent] = Option(new Child(1))