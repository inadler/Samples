import java.io._

import akka.actor.Actor

/**
  * Created by Nadler on 3/12/2016.
  */
class FileWriterActor(fileName : String) extends Actor {

  def receive = {
    case count: Long => writeToFile(fileName, count.toString)
  }

  private def writeToFile(fileName: String, text: String) = {
    var writer: Writer = null

    try {
      val file = new File(fileName)
      writer = new BufferedWriter(new FileWriter(file))
      writer.write(text)
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
