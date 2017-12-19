package chapter9 {
  package ui {
    class Button(val label: String) {
      var buttonCnt = 0

      def click(): Unit = updateUI()

      def updateUI(): Unit = {
        buttonCnt += 1
      }
    }
  }

  package ui2 {
    class Button(val label: String) extends Clickable {

      protected def updateUI(): Unit = {}
    }
  }
}

