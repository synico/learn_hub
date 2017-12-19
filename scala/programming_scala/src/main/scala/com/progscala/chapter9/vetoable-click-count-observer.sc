import chapter9.ui2._
import chapter9.observer._

val button2 = new Button("Click Me") with VetoableClicks with ObservableClicks {
  override val maxAllowed = 2
}

val button1 = new Button("Click Me") with ObservableClicks with VetoableClicks {
  override val maxAllowed = 2
}

class ClickCountObserver extends Observer[Clickable] {
  var count = 0

  override def receiveUpdate(state: Clickable): Unit = count += 1
}

val bco11 = new ClickCountObserver
val bco12 = new ClickCountObserver

button1 addObserver bco11
button1 addObserver bco12

(1 to 5) foreach (_ => button1.click())

assert(bco11.count == 2, s"bco1.count ${bco11.count} != 2")
assert(bco12.count == 2, s"bco2.count ${bco12.count} != 2")

println("Success!")

val bco21 = new ClickCountObserver
val bco22 = new ClickCountObserver

button2 addObserver bco21
button2 addObserver bco22

(1 to 5) foreach (_ => button2.click())

assert(bco21.count == 2, s"bco1.count ${bco21.count} != 2")
assert(bco22.count == 2, s"bco2.count ${bco22.count} != 2")