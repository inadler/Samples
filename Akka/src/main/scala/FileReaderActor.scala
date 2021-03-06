import akka.actor.{Props, Actor}
import akka.event.Logging

/**
  * Created by Nadler on 3/12/2016.
  */
class FileReaderActor() extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case fileName: String => {
      log.info("reading " + fileName)
      val text = scala.io.Source.fromFile(fileName).mkString

      val wordsCounterActor = context.system.actorOf(Props[WordsCounterActor], name = "wordsCounterActor")
      wordsCounterActor ! text
    }
  }
}
