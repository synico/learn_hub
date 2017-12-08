package chapter9.observer

trait Observer[-State] {
  def receiveUpdate(state: State): Unit
}

trait Subject[StateG] {
  private var observers: List[Observer[StateG]] = Nil

  def addObserver(observer: Observer[StateG]): Unit = observers ::= observer

  def notifyObservers(state: StateG): Unit = observers foreach(_.receiveUpdate(state))
}

