
val states = List("Alabama", "Alaska", "Virginia", "Wyoming")

for {
  s <- states
} println(s)

for {
  s <- states
  c <- s
} yield s"$c-${c.toUpper}"

val maps = Map("Alabama" -> "phe", "Alaska" -> "nda", "Arizona" -> "emp")
for {
  entry <- maps
  key <- entry._1 + entry._2
} yield key

states flatMap (_.toSeq withFilter(_.isLower) map (c => s"$c-${c.toUpper}"))