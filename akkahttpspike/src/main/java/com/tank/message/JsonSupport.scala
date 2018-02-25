package com.tank.message

import org.json4s.{DefaultFormats, jackson}

trait JsonSupport {

  implicit val serialization = jackson.Serialization
  implicit val formats = DefaultFormats

}