import akka.actor.{Props, Actor}
import akka.event.Logging

/**
  * Created by Nadler on 3/12/2016.
  */
class WordsCounterActor() extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case text: String => {
      val wordCount = wordCounter(text)
      log.info("There are " + wordCount + " words in this article")

      val wordsCounterNotifierActor = context.system.actorOf(Props(new WordsCounterNotifierActor("result.txt")), name = "wordsCounterNotifierActor")
      wordsCounterNotifierActor ! wordCount
    }
  }

  private def wordCounter(text: String): Long = {
    return text.toLowerCase.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+").length
  }
}
