
class Dollar(val value: Float) extends AnyVal {
  override def toString = "$%.2f".format(value)
}

class Euro(val value: Float = 0.4F) extends AnyVal {
  override def toString = "$%.2f".format(value)
}

val benjamin = new Dollar(100)
val rosy = new Euro()
println(benjamin)