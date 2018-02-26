package com.tank.router

import akka.http.scaladsl.server.Directives._
import ch.megard.akka.http.cors.CorsDirectives._
import com.tank.common.InitSystem
import com.tank.message.{JsonSupport, Tag}
import de.heikoseeberger.akkahttpjson4s.Json4sSupport._


trait TagRouter extends JsonSupport {

  self: InitSystem =>

  val tagRouter = cors() {
    pathPrefix("tag") {
      entity(as[Tag]) { tag =>
        println(s"""name:${tag.name}""")
        complete(s"tag ok")
      } 
    }
  }
}
