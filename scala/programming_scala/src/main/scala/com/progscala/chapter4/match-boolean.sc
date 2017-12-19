val bools = Seq(true, false, "x")

for {
  bool <- bools
} {
  bool match {
    case true => println("Got heads")
    case _ => println("Got tails")
  }
}