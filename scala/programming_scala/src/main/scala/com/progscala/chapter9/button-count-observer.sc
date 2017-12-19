import chapter9.observer._
import chapter9.ui._

class ButtonCountObserver extends Observer[Button] {
  var count = 0;

  override def receiveUpdate(state: Button): Unit = {
//    state.increaseCnt()
//    println(s"button click count: ${state.buttonCnt}")
    count += 1
  }
}

val button = new ObservableButton("Click me")
val bco1 = new ButtonCountObserver
val bco2 = new ButtonCountObserver

button addObserver bco1
button addObserver bco2

(1 to 5) foreach (_ => button.click())

assert(bco1.count == 15)
assert(bco2.count == 5)