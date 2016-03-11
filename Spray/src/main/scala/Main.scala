import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http

//import scala.actors.migration.Timeout
import scala.concurrent.duration._

/**
  * Created by Nadler on 3/11/2016.
  */
object Main extends App {
  val host = "localhost";
  val port = 8080;

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("MyFirstHttpServer")

  // create and start our service actor
  val service = system.actorOf(Props[RestInterface], "HttpInterface")

  implicit val executionContext = system.dispatcher
  implicit val timeout = Timeout(5.seconds)

  // start a new HTTP server on port with our service actor as the handler
  IO(Http).ask(Http.Bind(service, interface = host, port = port))
    .mapTo[Http.Event]
    .map {
      case Http.Bound(address) =>
        println(s"REST interface bound to $address")
      case Http.CommandFailed(cmd) =>
        println("REST interface could not bind to " + s"$host:$port, ${cmd.failureMessage}")
        system.shutdown()
    }
}
