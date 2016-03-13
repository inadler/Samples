import akka.actor._

/**
  * Created by Nadler on 3/12/2016.
  */
object Main {
  def main(args: Array[String]):Unit = {
    if (args.length == 0) {
      println("Missing fileName argument")
      sys.exit(0)
    }

    val fileName = args(0)
    println("FileName = " + args(0))

    val system = ActorSystem("HelloSystem")
    val fileReaderActor = system.actorOf(Props[FileReaderActor], name = "fileReaderActor")
    fileReaderActor ! fileName
  }
}






