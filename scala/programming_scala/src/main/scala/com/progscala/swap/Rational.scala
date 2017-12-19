package com.progscala.swap



class Rational (n: Int, d: Int) {
  println("Created " + n + "/" + d);
  
  require(d != 0)
  
  val number: Int = n
  val denom: Int = d
  
  def this(n: Int) = {
    this(n, 1)
    println("another constructor");
  }
  
  override def toString = n + "/" + d
  
  def add(that: Rational): Rational = 
    new Rational(
        number * that.denom + that.number * denom,
        denom * that.denom
        )
  
  def + (i: Int): Rational =
    new Rational(number + i * denom, denom)
  
  println("End")
  
}

object test {
  def main(args: Array[String]) {
    new Rational(4);
  }
}