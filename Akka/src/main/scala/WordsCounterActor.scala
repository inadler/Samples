import akka.actor.{Props, Actor}
import akka.event.Logging

import scala.collection.mutable

/**
  * Created by Nadler on 3/12/2016.
  */
class WordsCounterActor() extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case text: String => {
      val wordCount = countWords(text)
      log.info("There are " + wordCount.size + " words in this article")

      val wordsCounterNotifierActor = context.system.actorOf(Props(new FileWriterActor("result.txt")), name = "wordsCounterNotifierActor")
      wordsCounterNotifierActor ! wordCount
    }
  }

  def countWords(text: String) = {
    val counts = mutable.Map.empty[String, Int].withDefaultValue(0)

    for (rawWord <- text.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+")) {
      val word = rawWord.toLowerCase
      counts(word) += 1
    }
    counts
  }
}
