import scala.annotation.tailrec

// @tailrec
def factorial(i: BigInt): BigInt = {
  if (i == 1) i
  else i*factorial(i - 1)
}

for (i <- 1 to 10)
  println(s"$i:\t${factorial(i)}")