import chapter9.observer._
import chapter9.ui._

val button = new Button("Click me") with Subject[Button] {

  override def click(): Unit = {
    super.click()
    notifyObservers(this)
  }

}

class ButtonCountObserver extends Observer[Button] {

  var count = 0

  override def receiveUpdate(state: Button): Unit = count += 1

}

val bco1 = new ButtonCountObserver
val bco2 = new ButtonCountObserver

button addObserver bco1
button addObserver bco2

val seq = (1 to 5)
seq foreach println _
(1 to 5) foreach(_ => button.click())

println(s"count1: ${bco1.count} , count2: ${bco2.count}")

assert(bco1.count == 5)
assert(bco2.count == 5)

println("Success!")