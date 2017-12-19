package chapter14 {
  class Parent(val value: Int) {
    override def toString: String = s"${this.getClass.getName}($value)"
  }

  class Child(value: Int) extends Parent(value = 0)

  class Child2(value: Int) extends Parent(value)

}

