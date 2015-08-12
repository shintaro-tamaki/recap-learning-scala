package com.github.tamaki.study.tamaki.Refactor

import scala.util.{Success, Failure, Try}

/**
 * Created by shintaro.tamaki on 2015/08/12.
 */
object RefactorSample {
  def main(args: Array[String]): Unit = {
    // Q1.
    val nameOpt = Option("なまえ")
    nameOpt.isDefined match {
      case true =>
        // do something
        None
      case false =>
        // do something
        Some("x")
    }
    // Answer1-1.
    nameOpt match {
      case Some(x) => None
      case None => Some("x")
    }

    // Answer1-2.
    nameOpt.fold[Option[String]] {
      Some("x")
    } { x =>
      None
    }


    // Q2.
    if(nameOpt.isDefined) {
      val name = nameOpt.get
    }
    // Answer2.
    nameOpt.foreach{ name =>
      //　何か処理
    }

    // Q3.
    nameOpt.foreach { name =>
      if(name.trim.nonEmpty) {
        println("何かをする")
      }
    }
    // Answer3.
    nameOpt
      .withFilter(p => p.trim.nonEmpty)
      .foreach { x =>
      println("何かをする")
    }


    // Q4.
    val values = List(1, 2, 3)
    val r1 = values.length match {
      case 0 =>
        0
      case 1 =>
        val h = values.head
        println(h)
        h
      case 2 =>
        val h1 = values(0)
        val h2 = values(1)
        h1 + h2
      case 3 =>
        val h1 = values(0)
        val h3 = values(2)
        h1 + h3
    }
    println(s"===== Q4 r1 $r1")
    // Answer4.
    val answerR1 = values match {
      case Nil => 0
      case x :: Nil =>
        println(x)
        x
      case y1 :: y2 :: Nil =>
        y1 + y2
      case z1 :: z2 :: z3 :: Nil =>
        z1 + z3
      case _ => // 何もしない
    }
    println(s"===== Answer4 answerR1 $answerR1")


    // Q5
    val x1: String = "abc"
    val x2: String = ""
    val x3: Option[String] = Some("abc")
    val x4: Option[String] = Some("")
    val x5: Option[String] = Some("12345")
    val x6: Option[String] = Some("not a number")

    val errors = new scala.collection.mutable.HashMap[String, String]()

    if(x1.isEmpty) {
      errors.put("x1", "error.required")
    }
    if(x2.isEmpty) {
      errors.put("x2", "error.required")
    }
    if(x3.isDefined && !x3.get.isEmpty) {
      errors.put("x3", "error.required")
    }
    if(x4.isDefined && !x4.get.isEmpty) {
      errors.put("x4", "error.required")
    }
    if(x5.isDefined && !x5.get.isEmpty) {
      try {
        x5.get.toInt
      } catch {
        case e: Exception =>
          errors.put("x5", "error.number")
      }
    }
    if(x6.isDefined && !x6.get.isEmpty) {
      try {
        x6.get.toInt
      } catch {
        case e: Exception =>
          errors.put("x6", "error.number")
      }
    }
    println(s"===== Q5 errors $errors")



    // Answer5.
    implicit class RichOptionString(optStr: Option[String]) {
      def chkRequired(key: String):Option[(String, String)] = {
        optStr match {
          case None => Some((key, "error.required"))
          case Some(x) if x.isEmpty => Some((key, "error.required"))
          case _ => None
        }
      }

      def chkInt(key: String):Option[(String, String)] = {
        Try(optStr
          .withFilter(_.nonEmpty)
          .map(_.toInt)) match {
          case Failure(ex) => Some((key, "error.number"))
          case Success(x) => None
        }
      }
    }

    implicit def RichString(str: String): RichOptionString = RichOptionString(Option(str))


    val answerErrors = Seq(
      x1.chkRequired("x1"),
      x2.chkRequired("x2"),
      x3.chkRequired("x3"),
      x4.chkRequired("x4"),
      x5.chkInt("x5"),
      x6.chkInt("x6")
    ).flatten
      .toMap
    println(s"===== Answer5 answerErrors $answerErrors")

  }

}

