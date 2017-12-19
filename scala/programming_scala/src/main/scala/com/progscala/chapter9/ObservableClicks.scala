package chapter9.ui2

import chapter9.observer._
import chapter9.ui2._

trait ObservableClicks extends Clickable with Subject[Clickable] {
  abstract override def click(): Unit = {
    println("ObservableClicks.click")
    super.click()
    notifyObservers(this)
  }
}
