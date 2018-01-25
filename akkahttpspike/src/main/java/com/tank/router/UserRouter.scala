package com.tank.router

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import ch.megard.akka.http.cors.CorsDirectives._

trait UserRouter {

  val userRouter: Route = cors(){
    path("user") {
      pathEndOrSingleSlash {
        get {
          complete("ok")
        }
      }
    }
  }
}
