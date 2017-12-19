package chapter14.structuraltypes

trait SubjectFunc {

  type State

  type Observer = State => Unit

  private var observers: List[Observer] = Nil

  def addObserver(observer: Observer): Unit = observers ::= observer

  def notifyObservers(state: State): Unit = observers foreach (o => o(state))

}
