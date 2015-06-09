package com.github.tamaki.study.tamaki.FizzBuzz

import scala.collection.immutable.IndexedSeq

/**
 * Created by tamaki on 2015/02/08.
 */
object FizzBuzz {
  def main(args: Array[String]): Unit = {

    fizzbuzz((1 to 100).toList).map(println)

  }

  private def fizzbuzz(list: List[Int]): List[String] = {
    list.map {
      case i:Int if (i % 15 == 0) => "FizzBuzz"
      case i:Int if (i % 3 == 0) => "Fizz"
      case i:Int if (i % 5 == 0) => "Buzz"
      case i:Int => i.toString
    }
  }

}
