package com.tank.message

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import org.json4s.CustomSerializer
import org.json4s.JsonAST.JString

case object ZDTSerializer extends CustomSerializer[ZonedDateTime](format => ({
  case JString(s) => {
    println(s)
    ZonedDateTime.parse(s)
  }
}, {
  case zdt: ZonedDateTime => JString(zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")))
}))