import org.specs2.mutable.Specification
import spray.routing.HttpService
import spray.testkit.Specs2RouteTest

/**
  * Created by Nadler on 3/11/2016.
  */
class TestRestApi extends Specification with Specs2RouteTest with RestApi {
  def actorRefFactory = system // connect the DSL to the test ActorSystem

  "The service" should {

    "return a response with the reveresd value for the queryString parameter 'abcd'" in {
      Get("/reverse?word=abcd")  ~> route ~> check {
        responseAs[String] === "dcba"
      }
    }

    "return a response with the reveresd value for the queryString parameter ''" in {
      Get("/reverse?word=")  ~> route ~> check {
        responseAs[String] === ""
      }
    }

    "ignore request on missing parameter" in {
      Get("/reverse")  ~> route ~> check {
        handled must beFalse
      }
    }

   "leave GET requests to other paths unhandled" in {
      Get("/unhandled") ~> route ~> check {
        handled must beFalse
      }
    }

    /* "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put() ~> sealRoute(route) ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    }

    "return a MethodNotAllowed error for POST requests to the root path" in {
      Post() ~> sealRoute(route) ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    } */
  }
}
