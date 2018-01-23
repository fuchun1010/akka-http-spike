package com.tank.common

import java.io.File

import com.typesafe.config.ConfigFactory

import scala.util.Try

trait InitSystem {
  private val rootDirPath = new File(".").getAbsolutePath.replace(".", "")

  def getOrCreateDir(name: String): String = {
    val path = rootDirPath + name + File.separator
    val target = new File(path)
    target.isDirectory match {
      case true => target.getAbsolutePath
      case _ => {
        target.mkdirs()
        target.getAbsolutePath
      }
    }
  }

  def readSystemConfig(): Try[SystemConfig] = {
    val config = ConfigFactory.load()
    val ip = config.getString("http.hosts")
    val port = config.getInt("http.port")
    Try(SystemConfig(ip, port))
  }

  case class SystemConfig(ip: String, port: Int)

}
