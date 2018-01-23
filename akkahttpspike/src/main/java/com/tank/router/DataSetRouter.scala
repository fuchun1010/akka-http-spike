package com.tank.router

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

trait DataSetRouter {
  val dataSetRouter: Route = path("dataset") {
    pathEndOrSingleSlash {
      get {
        complete("hello dataSet")
      }
    }
  }
}
