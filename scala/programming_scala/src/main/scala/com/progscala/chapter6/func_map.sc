
val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  ("Alaska", "Juneau")
)

val stateCaptialsUp1 = stateCapitals map { kv => (kv._1, kv._2.toUpperCase) }
val stateCaptialsUp2 = stateCapitals map { case(k, v) => (k, v.toUpperCase) }

val states = Set("Alabama", "Alaska", "Arizona")
states map( k => k.length)

stateCapitals.foreach( kv => println(kv._1 + ":" + kv._2) )
stateCapitals foreach ( kv => println(kv._1 + ":" + kv._2) )
