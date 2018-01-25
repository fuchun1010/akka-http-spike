package com.tank.router

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import ch.megard.akka.http.cors.CorsDirectives._
import com.tank.common.InitSystem

trait DataSetRouter {
  self: InitSystem =>
  val dataSetRouter: Route = cors() {
    path("dataset") {
      pathEndOrSingleSlash {
        get {
          complete("hello dataSet")
        }
      }
    }
  }
}
