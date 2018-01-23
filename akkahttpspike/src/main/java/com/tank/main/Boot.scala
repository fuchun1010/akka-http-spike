package com.tank.main

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.tank.router.CustomerRouter

import scala.concurrent.ExecutionContext

object Boot extends App with CustomerRouter {

  def startServer(): Unit = {
    implicit val actorSystem = ActorSystem("akka-http-demo")
    implicit val executionContext: ExecutionContext = actorSystem.dispatcher
    implicit val actorMaterializer: ActorMaterializer = ActorMaterializer()
    Http().bindAndHandle(customerRouter, "0.0.0.0", 6000)
    println("start server success")
  }

  startServer()


}
