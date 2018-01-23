package com.tank.main

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.tank.common.{InitSystem, SparkSessionQueue}
import com.tank.router.CustomerRouter

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

object Boot extends App with SparkSessionQueue with CustomerRouter with InitSystem {

  initSparkSessionQueue()

  getOrCreateDir("log")

  getOrCreateDir("upload")

  def startServer(): Unit = {

    implicit val actorSystem = ActorSystem("akka-http-demo")
    implicit val executionContext: ExecutionContext = actorSystem.dispatcher
    implicit val actorMaterializer: ActorMaterializer = ActorMaterializer()
    readSystemConfig() match {
      case Success(systemConfig) => {
        println("start server success")
        Http().bindAndHandle(customerRouter, systemConfig.ip, systemConfig.port)
      }
      case Failure(e) => {
        println(e)
      }
    }
  }

  startServer()


}
