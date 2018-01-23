package com.tank.router

import akka.http.scaladsl.server.Directives._
import com.tank.common.InitSystem

trait CustomerRouter extends UserRouter with DataSetRouter with InitSystem {


  val customerRouter = userRouter ~ dataSetRouter
}
