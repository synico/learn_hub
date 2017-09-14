object ForeachDemo {

  def main(args: Array[String]): Unit = {
    arrayDemo()
  }

  val myArray = new Array[String](3)

  //step2
  def defineVar(): Unit = {
    val finalVal: String = "this is final variable"
    var msg = "this is variable"
    println(finalVal)
    println(msg)
  }

  //step3
  def max(x: Int, y: Int): Int = {
    if (x > y)
      x
    else
      y
  }

  //step6
  def testForeach(): Unit = {
    //option1
    myArray.foreach(arg => println(arg))
    //option2
    myArray.foreach(println)
  }

  //step8
  def arrayDemo(): Unit = {
    //option1
    println("option1------------------")
    val myArray1 = new Array[String](3)
    myArray1(0) = "idx1"
    myArray1(2) = "idx3"

    //option2
    println("option2------------------")
    val myArray2 = Array("idx1","idx2","idx4")
    myArray2.foreach(println)

    //option3
    println("option3------------------")
    val myArray3 = Array.apply("idx1", "idx2")
    myArray3.foreach(println)
  }

}
