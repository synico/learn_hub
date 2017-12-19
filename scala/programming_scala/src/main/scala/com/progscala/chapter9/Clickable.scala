package chapter9.ui2

trait Clickable {

  def click(): Unit = updateUI()

  protected def updateUI(): Unit
}
