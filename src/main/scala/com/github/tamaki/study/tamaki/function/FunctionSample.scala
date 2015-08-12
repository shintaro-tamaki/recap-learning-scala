package com.github.tamaki.study.tamaki.function

/**
 * Created by shintaro.tamaki on 2015/08/12.
 */
object FunctionSample {
  def main(args: Array[String]): Unit = {

    println(createAdder(3)(5))
    println(area(3.14)(10))
    val adder31 = adder(3, _:Int)
    println(adder31(10))

  }

  def createAdder(x: Int): (Int) => Int = {
    (y: Int) => x + y
  }

  def area(x: BigDecimal): (BigDecimal) => BigDecimal = {
    (y: BigDecimal) => y * y * x
  }

  def adder(x: Int, y: Int) = x + y


}
