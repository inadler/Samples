import akka.actor.Actor
import spray.routing.{HttpService, Route}


/**
  * Created by Nadler on 3/11/2016.
  */
// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class RestInterface extends Actor with RestApi {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(route)
}


// this trait defines our service behavior independently from the service actor
trait RestApi extends HttpService  {

  val route: Route = {
    (path("reverse") & get) {
      parameter("word") { word => {
        val reversedWord = new StringBuilder(word).reverse.toString()
        complete(reversedWord)
      } }
    }
  }
}