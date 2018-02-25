package com.tank.message

import org.json4s.{DefaultFormats, jackson}
import  org.json4s.ext.JodaTimeSerializers._
trait JsonSupport {

  implicit val serialization = jackson.Serialization
  implicit val formats = DefaultFormats ++ all

}