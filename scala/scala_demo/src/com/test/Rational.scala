package com
package test

class Rational (n: Int, d: Int) {
  println("--------Created " + n + "/" + d);
  
  require(d != 0)
  
  private val g = gcd(n.abs, d.abs)
  val number: Int = n / g
  val denom: Int = d / g
  
  def this(n: Int) = {
    this(n, 1)
    println("--------another constructor");
  }
  
  def this() = {
    this(3)
  }
  
  override def toString = n + "/" + d
  
  def add(that: Rational): Rational = 
    new Rational(
        number * that.denom + that.number * denom,
        denom * that.denom
        )
  
  // A method to calculate greatest common divisor
  def gcd(a: Int, b: Int): Int = {
    if (b == 0)
      a
    else
      gcd(b, a % b)
  }
  
  def + (that: Rational): Rational =
    new Rational(number * that.denom + that.number * denom, denom * that.denom)
  
  def * (that: Rational): Rational = 
    new Rational(number * that.number, denom * that.denom)
  println("--------End")
}


object TestRational {
  
  implicit def int2Rational(x: Int) = new Rational(x);
  
  def main(args: Array[String]) {
    val rational = new Rational(66, 42);
    val oneHalf = new Rational(1, 1)
    println("result: " + 2*rational)
  }
}