package com.github.tamaki.study.implicits

import org.joda.time.DateTime

/**
 * Created by shintaro.tamaki on 2015/07/31.
 */
object ImplicitFunctionSample extends App {

  class hoge {

  }

  class Fuga {

  }


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

