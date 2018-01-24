package com.tank.router

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

trait UserRouter {

  val userRouter: Route = path("user") {
    pathEndOrSingleSlash {
      get {
        complete("ok")
      }
    }
  }
}
