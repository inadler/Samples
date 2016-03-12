/**
  * Created by Nadler on 3/12/2016.
  */

import akka.actor._



object Main extends App {
  val system = ActorSystem("HelloSystem")

  val urlReaderActor = system.actorOf(Props[UrlReaderActor], name = "urlReaderActor")

  urlReaderActor ! "http://www.csd.uwo.ca/~magi/personal/humour/Computer_Audience/Computer%20Limericks.html"
}






