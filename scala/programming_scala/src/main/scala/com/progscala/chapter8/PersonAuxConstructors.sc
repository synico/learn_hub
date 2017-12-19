import chapter8.{Address, Person}

val a1 = new Address("1 Scala Lane", "Anytown", "CA", "98765")

val a2 = new Address("98765")

val p1 = new Person("Buck Trend1")

val p2 = new Person("Buck Trend2", Some(20), Some(a1))

val p3 = new Person("Buck Trend3", 20, a2)

val p4 = new Person("Buck Trend4", 20)