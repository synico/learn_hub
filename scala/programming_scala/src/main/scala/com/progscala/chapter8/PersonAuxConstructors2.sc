
import chapter8.Address

val a1 = new Address("1 Scala Lane", "Anytown", "CA", "98765")

val a2 = new Address("98765")

case class Person2(name: String, age: Option[Int] = None, address: Option[Address] = None)

val p1 = Person2("Buck Trend1")

val p2 = new Person2("Buck Trend2", Some(20), Some(a1))

val p3 = new Person2("Buck Trend3", Some(20))

val p4 = Person2("Buck Trend4", address = Some(a2))