package com.github.tamaki.study.rational

/**
 * Created by shintaro.tamaki on 2015/07/07.
 */
case class Rational(numerator: Int, denominator: Int) {
  def +(x:Rational): Rational = {
    assert(x.denominator == this.denominator)
    Rational(
      numerator = x.numerator + this.numerator,
      denominator = x.denominator
    )
  }

  def <(x:Rational): Boolean = {
    assert(x.denominator == this.denominator)
    x.numerator < this.numerator
  }

  def <=(x:Rational): Boolean = {
    assert(x.denominator == this.denominator)
    x.numerator <= this.numerator
  }

  def >(x:Rational): Boolean = {
    assert(x.denominator == this.denominator)
    x.numerator > this.numerator
  }

  def >=(x:Rational): Boolean = {
    assert(x.denominator == this.denominator)
    x.numerator >= this.numerator
  }
}

// インタフェースも実装も持つtrait
trait MyComparable2[A] {
  def <(that: A): Boolean
  def >(that: A): Boolean = ! (this <= that)
  def <=(that: A): Boolean = (this < that) || (this == that)
  def >=(that: A): Boolean = (this > that) || (this == that)
}

// オレオレインタフェース実装
case class Rational3(n: Int, d: Int) extends MyComparable2[Rational3] {
  override def <(that: Rational3): Boolean = true
}

// Scalaの Ordered Traitを使った実装
// Javaのjava.lang.Comparable
case class Rational2(n: Int, d: Int) extends Ordered[Rational2] {
  override def compare(that: Rational2): Int =
    (this.n * that.d) - (that.n * this.d)
}

object RationalMain {
}
