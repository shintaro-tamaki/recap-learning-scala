package com.github.tamaki.study.future

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by shintaro.tamaki on 2015/07/08.
 */
object Main extends App {
  println("Main開始")

  val f = Future {
    // ここから非同期にしたい
    Thread.sleep(5000)
    println("終わったよ")
    // ここまで
  }

  Await.result(f, Duration.Inf)
  println("Main終了")
}
