package com.github.tamaki.study.tamaki.FizzBuzz

import scala.annotation.tailrec
import scala.util.Try

/**
 * Created by tamaki on 2015/02/08.
 */
object FizzBuzz {
  def main(args: Array[String]): Unit = {

   (1 to 100).map(fizzbuzz).map(println)

    //１行で書く
    (1 to 100).map(i => if (i % 15 == 0) {"FizzBuzz"} else if (i % 3 == 0) {"Fizz"} else if (i % 5 == 0) {"Buzz"} else {i.toString}).foreach(println)

    //2の倍数を除去
    (1 to 100)
      .filterNot(p => p % 2 == 0)
      .map(fizzbuzz)
      .foreach(println)

    //その４
    fizzbuzzList((1 to 100)).mkString(",")

    //その５
    val result = (1 to 100)
      .map(fizzbuzz)
      .withFilter(_.matches("""\d+"""))
      .map(_.toInt)
      .sum

    println(result)

    //その５
    val result2 = (1 to 100)
      .map(fizzbuzz)
      .flatMap(p => Try(p.toInt).toOption)
      .sum

    println(result2)

    println(sum(10))

  }

  private def fizzbuzzList(list: Seq[Int]): Seq[String] = {
    list.map {
      case i:Int if (i % 15 == 0) => "FizzBuzz"
      case i:Int if (i % 3 == 0) => "Fizz"
      case i:Int if (i % 5 == 0) => "Buzz"
      case i:Int => i.toString
    }
  }


  private def fizzbuzz(in:Int): String = {
    in match {
      case i:Int if (i % 15 == 0) => "FizzBuzz"
      case i:Int if (i % 3 == 0) => "Fizz"
      case i:Int if (i % 5 == 0) => "Buzz"
      case i:Int => i.toString
    }
  }

  @tailrec
  //doFizzBuzz6(1,100)
  def doFizzBuzz6(start: Int,end: Int):Unit = {
    def fizzbuzz(x : Int) = {
      x match {
        case x if x % 15 == 0 => println("FizzBuzz")
        case x if x % 5 == 0 => println("Buzz")
        case x if x % 3 == 0 => println("Fizz")
        case _ => println(x.toString)
      }
    }

    start match {
      case start if start <= end => {
        fizzbuzz(start)
        doFizzBuzz6(start+1,end)
      }
      case _ => Nil
    }
  }

  def sum(n: Int):Int = {
    @tailrec
    def sum0(acc: Int, n: Int):Int = {
      n match {
        case i: Int if (i == 1) => acc
        case i: Int if (i > 1) => sum0(acc + i, i - 1)
        case _ => throw new Exception()
      }
    }
    sum0(0, n)
  }

}
