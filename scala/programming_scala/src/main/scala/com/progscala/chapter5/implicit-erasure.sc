
object M {
  implicit object IntMarker
  implicit object StringMarker
  implicit object XMarker

  def m(seq: Seq[Int])(implicit i: XMarker.type): Unit = {
    println(s"Seq[Int]: $seq")
  }

  def m(seq: Seq[String])(implicit s: StringMarker.type): Unit = {
    println(s"Seq[String]: $seq")
  }

}

//object N {
//  def m(seq: Seq[Int]): Unit = {
//    println(s"Seq[Int]: $seq")
//  }
//
//  def m(seq: Seq[String]): Unit = {
//    println(s"Seq[String]: $seq")
//  }
//}

import M._
m(List(1,2,3))
m(List("one", "two", "three"))