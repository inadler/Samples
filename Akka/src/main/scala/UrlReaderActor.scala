import akka.actor.{Props, Actor}
import akka.event.Logging

/**
  * Created by Nadler on 3/12/2016.
  */
class UrlReaderActor() extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case address: String => {
      log.info("reading " + address)

      // get URL content
      var text = scala.io.Source.fromURL(address).mkString

      val wordsCounterActor = context.system.actorOf(Props[WordsCounterActor], name = "wordsCounterActor")
      wordsCounterActor ! text
    }
  }
}
