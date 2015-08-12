package com.github.tamaki.study.tamaki.future

import java.util.concurrent.Executors

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
//import scala.concurrent.ExecutionContext.Implicits.global
import dispatch._
import scala.util.{Failure, Success}

/**
 * Created by shintaro.tamaki on 2015/07/08.
 */
object FutureMain extends App {
  implicit val context = scala.concurrent.ExecutionContext.fromExecutor(Executors.newFixedThreadPool(20))

  def future1(): Unit = {
    println("Main開始")

    val f = Future {
      // ここから非同期にしたい
      Thread.sleep(5000)
      "終わったよ"
      // ここまで
    }

    f.map { s =>
      println(s)
    }

    Await.result(f, Duration.Inf)
    println("Main終了")
  }

  def future2(): Unit = {
    println("Main開始")

    val f = Future {
      // ここから非同期にしたい
      Thread.sleep(5000)
      "終わったよ"
      // ここまで
    }

    f.map { s =>
      println(s)
    }
    f.onSuccess{
      case s =>
        println(s"success $s")
    }
    f.onFailure{
      case ex =>
        println(s"failure $ex")
    }
    f.onComplete{
      case Success(s) =>
        println(s"onComplete success $s")
      case Failure(ex) =>
        println(s"onComplete failure $ex")
    }
    f.onComplete{
      case Success(s) =>
        println(s"onComplete2 success $s")
      case Failure(ex) =>
        println(s"onComplete2 failure $ex")
    }
    f.onComplete{
      case Success(s) =>
        println(s"onComplete3 success $s")
      case Failure(ex) =>
        println(s"onComplete3 failure $ex")
    }

    Await.result(f, Duration.Inf)

    Thread.sleep(5000)
    println("Main終了")
  }

  def future3(): Unit = {
    println("Main開始")

    val f = Future {
      // ここから非同期にしたい
      Thread.sleep(5000)
      new Exception("終わったよ")
      // ここまで
    }

    f.map { s =>
      println(s)
    }.recover {
      case ex:Exception =>
        println(s"recover $ex")
    }
    f.onSuccess {
      case s =>
        println(s"success $s")
    }
    f.onFailure {
      case ex =>
        println(s"failure $ex")
    }
    f.onComplete {
      case Success(s) =>
        println(s"onComplete success $s")
      case Failure(ex) =>
        println(s"onComplete failure $ex")
    }
    f.onComplete {
      case Success(s) =>
        println(s"onComplete2 success $s")
      case Failure(ex) =>
        println(s"onComplete2 failure $ex")
    }
    f.onComplete {
      case Success(s) =>
        println(s"onComplete3 success $s")
      case Failure(ex) =>
        println(s"onComplete3 failure $ex")
    }

    Await.result(f, Duration.Inf)

    Thread.sleep(10000)
    println("Main終了")
  }

  def future4(): Unit = {
    println("Main開始")
    val f = Future {
      // ここから非同期にしたい
      Thread.sleep(5000)
      "終わったよ"
      // ここまで
    }
    f.filter(p => p == "終わったよ").map(
      println
    )
    Await.result(f, Duration.Inf)
    Thread.sleep(10000)
    println("Main終了")
  }

  def future5(): Unit = {
    println("Main開始")
    val f = Future {
      // ここから非同期にしたい
      Thread.sleep(5000)
      "終わったよ"
      // ここまで
    }

    val ret: Future[String] = f.filter(p => p == "a")
      .map{
      p => {
        println(p)
        p
      }
    }
      .recover{
      case _ =>
        println("recover")
        "go"
    }
    Await.result(f, Duration.Inf)
    Thread.sleep(10000)
    println("Main終了")
  }

  def future6(): Unit = {
    println("Main開始")

    def getUserInfo(userName:String): Future[String] = {
      Future(userName)
    }

    def getTweets(userName:String): Future[List[String]] = {
      Future{
        List("tweet1", "tweet2", "tweet3")
      }
    }

    val userFuture: Future[String] = getUserInfo("tanacasino")
    val tweetsFuture: Future[List[String]] = getTweets("tanacasino").filter(f => f.size > 5 )
    val userTweets: Future[(String, List[String])] = userFuture.zip(tweetsFuture)
    userTweets.foreach { case (user, tweets) =>
      println(s"User: $user")
      tweets.foreach(println)
    }
    Await.result(userTweets, Duration.Inf)
    Thread.sleep(10000)
    println("Main終了")
  }

  def get(endpoint: String): Future[Either[Throwable, String]] = {
    val request = url(endpoint).GET
    Http(request OK as.String).either
  }

  def future7(): Unit = {
    // こんな感じで使える
    get("http://www.yahoo.co.jp/").onSuccess {
      case Left(t) => t.printStackTrace()
      case Right(s) => println(s)
    }

    get("http://www.yahoo.co.jp/").map { s =>
      println("=========== yahoo")
      println(s)
      get("http://www.google.co.jp/").onSuccess {
        case Left(t) => t.printStackTrace()
        case Right(s) =>
          println("=========== google")
          println(s)
      }
    }
  }

  def future8(): Unit = {
    def aggregate(id: String): Int = {
      val m = Map("rikunabi" -> 10, "mynavi" -> 100, "en" -> 11)
      m.getOrElse(id, throw new IllegalArgumentException("id not found"))
    }

    val f: Seq[dispatch.Future[Int]] = Seq("rikunabi", "mynavi", "en")
      .map( id => Future { aggregate(id) })
    val seq = Future.sequence(f)
    Await.result(seq, Duration.Inf)

    val result = seq.map{ f =>
      println(f.sum)
    }

    Thread.sleep(10000)
    println(s"======= future8 end.")
  }

//  def future9(): Unit = {
//    val cluster1 = Seq("server1", "server2")
//    val cluster2 = Seq("server3", "server4")
//    val clusters = Seq(cluster1, cluster2)
//
//    val result = clusters.map { cluster =>
//      val clusterFutures = cluster.map { m =>
//        Future {
//          post(s"http://$m/restore")
//        }
//      }
//      val seqFuture = Future.sequence(clusterFutures)
//      seqFuture.filter(restoreResults => restoreResults.forall(_.isRight))
//        .flatMap { result =>
//        val f = Future.sequence(cluster.map(s => Future {post(s"http://$s/activate"})))
//        f.
//
//      }
//
//    }
//
//
//    def get(url:String): String = {
//      s"request to $url"
//    }
//
//
//
//    import dispatch._, Defaults._
//
//    def post(url: String): Future[Either[Throwable, String]] = Future{
//      Right(url)
//    }
//
//    Thread.sleep(10000)
//    println(s"======= future8 end.")
//  }

  //  future1()
//  future2()
//  future3()
//  future4()
//  future5()
//  future6()
  //  future7()
  future8()
}

