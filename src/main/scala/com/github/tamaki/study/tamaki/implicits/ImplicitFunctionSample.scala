package com.github.tamaki.study.tamaki.implicits

/**
 * Created by shintaro.tamaki on 2015/07/31.
 */
object ImplicitFunctionSample extends App {

  // 1. 型クラスを定義する
  trait Adder[T] {
    def add(x: T, y: T): T
  }
  // 2. 型クラスを扱う関数を定義する
  def add[T](x: T, y: T)(implicit adder: Adder[T]) = {
    adder.add(x, y)
  }

  // 3. 型クラスのインスタンスを作る
  // Int型対応 val で書く場合
  implicit val intAdder = new Adder[Int] {
    def add(x: Int, y: Int): Int = x + y
  }
  // String型対応 object で書く場合
  implicit object StringAdder extends Adder[String] {
    def add(x: String, y: String): String = x + y
  }
  // オレオレな型に対応
  case class Hoge(value: Int)
  implicit object HogeAdder extends Adder[Hoge] {
    def add(x: Hoge, y: Hoge): Hoge = {
      Hoge(x.value + y.value)
    }
  }

  println(add("a", "b"))
  println(add(1, 2))
  println(add(Hoge(1), Hoge(2)))

  List(1,2).sum
}

