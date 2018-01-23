package com.tank.router

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.tank.common.InitSystem

trait DataSetRouter {
  self: InitSystem =>
  val dataSetRouter: Route = path("dataset") {
    pathEndOrSingleSlash {
      get {
        complete("hello dataSet")
      }
    }
  }
}
