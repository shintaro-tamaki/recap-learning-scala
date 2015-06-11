package com.github.tamaki.study.tamaki.FizzBuzz

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
    case i:Int if (i % 15 == 0) => "FizzBuzz"
    case i:Int if (i % 3 == 0) => "Fizz"
    case i:Int if (i % 5 == 0) => "Buzz"
    case i:Int => i.toString
  }

}
