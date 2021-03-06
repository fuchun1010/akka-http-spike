package com.tank.common

import java.util.concurrent.{ArrayBlockingQueue, BlockingQueue}

import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession

trait SparkSessionQueue {

  val sparkSessionThreshold = () => {
    val config = ConfigFactory.load()
    config.getInt("sessionThreshold")
  }

  private val sessionQueue: BlockingQueue[SparkSession] = new ArrayBlockingQueue[SparkSession](sparkSessionThreshold())

  def initSparkSessionQueue(): Unit = {
    val session = SparkSession.builder()
      .appName("akka-http-spike-spark")
      .master("local[*]")
      .config("spark.executor.memory", "2G")
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .config("spark.scheduler.mode", "FAIR")
      .config("spark.scheduler.pool", "spike_pool")
      .getOrCreate()
    for (i <- 1 to sparkSessionThreshold()) {
      sessionQueue.add(session.newSession())
    }
  }

  def close(session: SparkSession): Unit = {
    sessionQueue.add(session)
  }

  def getSession(): SparkSession = sessionQueue.take()

  def runWithSession(callback: SparkSession => Unit): Unit = {
    val session = getSession()
    callback(session)
    close(session)
  }

  def size(): Int = sessionQueue.size()

}
