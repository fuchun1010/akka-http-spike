package com.tank.router

import akka.http.scaladsl.server.Directives._
import com.tank.common.{InitSystem, SparkSessionQueue}

trait CustomerRouter extends UserRouter
    with DataSetRouter
    with SpikeRouter
    with SparkSessionQueue
    with InitSystem {


  val customerRouter = userRouter ~ dataSetRouter ~ spikeRouter
}
