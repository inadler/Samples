import java.io._

import akka.actor.Actor

/**
  * Created by Nadler on 3/12/2016.
  */
class FileWriterActor(fileName : String) extends Actor {

  def receive = {
    case wordCount => writeToFile(fileName, wordCount)
  }

  private def writeToFile(fileName: String, wordCount: Any) = {
    var writer: Writer = null

    try {
      val file = new File(fileName)
      writer = new BufferedWriter(new FileWriter(file))

      for (word <- wordCount.asInstanceOf[ scala.collection.mutable.Map[String,Int]]) {
        writer.write(word._1 + " " + word._2 + "\n")
      }
    } catch {
      case ex: FileNotFoundException => ex.printStackTrace()
      case ex: IOException => ex.printStackTrace()
    } finally {
      try {
        if (writer != null) {
          writer.close()
        }
      } catch {
        case ex: IOException => ex.printStackTrace()
      }
    }
  }
}
