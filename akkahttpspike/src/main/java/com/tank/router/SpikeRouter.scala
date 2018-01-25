package com.tank.router

import java.io.File

import akka.http.scaladsl.server.Directives._
import com.tank.common.{InitSystem, SparkSessionQueue}
import org.apache.spark.sql.functions._
import ch.megard.akka.http.cors.CorsDirectives._

trait SpikeRouter {

  self: InitSystem with SparkSessionQueue =>

  val spikeRouter = cors() {
    pathPrefix("spike") {
      val parquetPath = parquetDir() + "user"

      pathEndOrSingleSlash {
        get {
          runWithSession((session) => {
            val start = System.currentTimeMillis()
            session.read.parquet(parquetPath).schema.foreach(f => println(f.name))
            val end = System.currentTimeMillis()
            println("cost time ->" + (end - start))
          })
          complete("ok")
        }
      } ~ pathPrefix("single") {
        get {
          val session = getSession()
          val fileDir = new File(parquetPath)
          val headFilePath = fileDir.listFiles().head.getAbsolutePath
          val start = System.currentTimeMillis()
          session.read.parquet(headFilePath).schema.foreach(f => println(f.name))
          val end = System.currentTimeMillis()
          close(session)
          println(this.size())
          println("cost time -> " + (end - start))
          complete("single")
        }
      } ~ pathPrefix("join") {
        get {
          val session = getSession()
          val dfA = session.read.parquet(parquetPath).as("dfA")
          val dfB = session.read.parquet(parquetPath).as("dfB")

          val rs = dfA.join(dfB, col("dfA._id") === col("dfB._id"), "inner").repartition(64)
          rs.show(10)
          close(session)
          complete("join")
        }
      }
    }
  }
}
