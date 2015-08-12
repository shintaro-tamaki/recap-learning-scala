package com.github.tamaki.study.tamaki.implicits

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
 * Created by shintaro.tamaki on 2015/07/31.
 */
object ImplicitSample extends App {

  val now = new DateTime()
  println(now)
  println(now + 1.day)
  println(now + 6.month)
  println(now + 2.year)


  implicit class RichDateTime(val dateTime: DateTime) {
    def +(du: Duration) = dateTime
      .plusDays(du.day)
      .plusHours(du.month)
      .plusYears(du.year)
  }

  case class Duration(
    day: Int = 0,
    month: Int = 0,
    year: Int = 0
  )

  implicit class RichInt(val i: Int) {
    def day: Duration = Duration(day = i)
    def month: Duration = Duration(month = i)
    def year: Duration = Duration(year = i)
  }
}

