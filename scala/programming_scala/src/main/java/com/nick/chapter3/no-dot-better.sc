def isEven(n: Int) = (n % 2) == 0

List(1, 2, 3, 4) filter isEven foreach println

println("no-dot-better")

(1 to 10 by 2) foreach println