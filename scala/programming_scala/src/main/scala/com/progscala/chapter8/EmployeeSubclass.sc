import chapter8.Address

case class Person(
  name: String,
  age: Option[Int] = None,
  address: Option[Address] = None)

class Employee(
  name: String,
  age: Option[Int] = None,
  address: Option[Address] = None,
  val title: String = "[unknown]",
  val manager: Option[Employee] = None) extends Person(name, age, address) {

  override def toString: String = s"Employee($name, $age, $address, $title, $manager)"
}

val a1 = new Address("1 Scala Lane", "AnyTown", "CA", "98765")
val a2 = new Address("98765")

val person1 = Person("Joe CEO")

val ceo = new Employee("Joe CEO", title = "CEO")

println(s"${person1 == ceo}")

val employee1 = new Employee("Buck Trends1")

val employee2 = new Employee("Buck Trends2", Some(20), Some(a1))

val employee3 = new Employee("Buck Trends3", Some(20), Some(a1), "Zombie Dev")

val employee4 = new Employee("Buck Trends4", Some(20), Some(a1), "Zombie Dev", Some(ceo))