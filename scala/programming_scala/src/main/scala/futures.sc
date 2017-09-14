import java.util.concurrent.TimeUnit

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

def sleep(millis: Long) = {
  TimeUnit.MILLISECONDS.sleep(millis)
}

// handle tasks
def doWork(index: Int) = {
  TimeUnit.MILLISECONDS.sleep((math.random * 1000).toLong)
  index
}

(1 to 5) foreach { index =>
  val future = Future {
    doWork(index)
  }
  future onSuccess {
    case answer: Int => println(s"Success! returned: $answer")
  }
  future onFailure {
    case th: Throwable => println(s"Failure! returned: $th")
  }
}

TimeUnit.MILLISECONDS.sleep(1000)
println("Finito!")