//import scala.beans.BeanProperty

class Name(value: String, prefix: String) {
  def printName: Unit = println(s"Name is : $value")
  def printPrefix = println(s"Prefix is: $prefix")

  def getName: String = value
  def getPrefix = prefix
}

object Name {

  def apply(value: String, prefix: String): Name = {
    println(s"apply Name: $value")
    println(s"apply Prefix: $prefix")
    new Name(value, prefix)
  }

}

val name1 = new Name("test1", "Mr")
name1 printName

name1 printPrefix

println("-------------------------")
println(s"print name1 : ${name1 getName}")
println(s"print name1 : ${name1.getPrefix}")

val name11 = Name("test11", "Miss")
val name11_value = name11 getName

println("-------------------------")
case class Age(var num: Int)

val age1 = Age(1)
println(s"Age: ${age1.num}")

val name2 = Name("test2", "Mis")

class Name2(s: String) {
  var value: String = s
}

val name3 = new Name2("Buck")
name3.value
name3.value_=("Golden")
name3.value
name3.value = "Timberland"
name3.value