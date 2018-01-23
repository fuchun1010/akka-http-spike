package com.tank.router

import akka.http.scaladsl.server.{Directives, Route}

trait UserRouter {

  val userRouter: Route = Directives.path("user") {
    Directives.pathEndOrSingleSlash {
      Directives.get {
        Directives.complete("ok")
      }
    }
  }
}
