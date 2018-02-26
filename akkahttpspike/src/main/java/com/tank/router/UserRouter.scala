package com.tank.router

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import ch.megard.akka.http.cors.CorsDirectives._
import com.tank.message.{JsonSupport, Person}
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._

trait UserRouter extends JsonSupport {

  val userRouter: Route = cors() {
    path("user") {
      pathEndOrSingleSlash {
        get {
          complete("ok")
        }
      }
    } ~ pathPrefix("person") {
      entity(as[Person]) { person =>
        complete(person)
      }
    }
  }
}
