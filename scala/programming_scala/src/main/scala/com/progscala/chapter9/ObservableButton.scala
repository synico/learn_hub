package chapter9.ui

import chapter9.observer._

class ObservableButton(name: String) extends Button(name: String) with Subject[Button] {

  override def click(): Unit = {
    super.click()
    notifyObservers(this)
  }

}
