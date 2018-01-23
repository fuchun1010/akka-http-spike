package com.tank.common

import java.io.File

import com.typesafe.config.ConfigFactory

import scala.util.Try

trait InitSystem {
  private val rootDirPath = new File(".").getAbsolutePath.replace(".", "")

  private def getOrCreateDir(name: String): String = {
    val path = rootDirPath + name + File.separator
    val target = new File(path)
    if (!target.exists()) {
      target.mkdir()
    }
    target.getAbsolutePath + File.separator
  }

  def readSystemConfig(): Try[SystemConfig] = {
    val config = ConfigFactory.load()
    val ip = config.getString("http.hosts")
    val port = config.getInt("http.port")
    Try(SystemConfig(ip, port))
  }

  val logDir = () => getOrCreateDir("log")

  val parquetDir = () => getOrCreateDir("parquet")

  val uploadDir = () => getOrCreateDir("upload")

  case class SystemConfig(ip: String, port: Int)

}
