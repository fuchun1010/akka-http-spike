package com.tank.router

import akka.http.scaladsl.server.Directives._

trait CustomerRouter extends UserRouter with DataSetRouter {


  val customerRouter = userRouter ~ dataSetRouter
}
