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
    println(h(10))
    println(i(10))
    println(f4(10))
    println(f5(10))
  }

  def createAdder(x: Int): (Int) => Int = {
    (y: Int) => x + y
  }

  def area(x: BigDecimal): (BigDecimal) => BigDecimal = {
    (y: BigDecimal) => y * y * x
  }

  def adder(x: Int, y: Int) = x + y

  val f = (x: Int) => x + 1
  val g = (x: Int) => x * 3
  val h = g.compose(f)
  val i = g.andThen(f)

  val f1 = (x: Int) => x + 1
  val f2 = (x: Int) => x * 2
  val f3 = (x: Int) => x - 1
  val f4 = chainFunction(f1, f2, f3)
  val f5 = chainFunction(f3, f2, f1)

  def chainFunction(functions: (Int => Int)*) = {
    functions.reduce((x, y) => x andThen y )
  }


}
