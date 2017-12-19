package chapter9.ui2

import chapter9.observer._

trait VetoableClicks extends Clickable {
  val maxAllowed = 1
  private var count = 0

  abstract override def click(): Unit = {
    println("VetoableClicks.click")
    if(count < maxAllowed) {
      count += 1
      super.click()
    }
  }
}
