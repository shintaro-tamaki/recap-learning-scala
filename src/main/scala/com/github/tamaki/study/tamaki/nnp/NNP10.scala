package com.github.tamaki.study.tamaki.nnp

/**
 * Created by tamaki on 2015/02/08.
 */
trait NNP10 {

  // P01 (*) Find the last element of a list.
  def last(list: List[Int]): Int = {
    list.last
  }

  @scala.annotation.tailrec
  final def last2(list: List[Int]): Int = {
    list match {
      case x :: Nil => x
      case x1 :: xs => last2(xs)
      case _ => throw new IllegalArgumentException
    }
  }

  // P02 (*) Find the last but one element of a list.
  def penultimate(list: List[Int]): Int = {
    list.init.last
  }

  @scala.annotation.tailrec
  final def penultimate2(list: List[Int]): Int = {
    list match {
      case Nil => throw new NoSuchElementException
      case x :: Nil => throw new NoSuchElementException
      case x1 :: x2 :: Nil => x1
      case x :: xs => penultimate2(xs)
    }
  }


  def nth(n: Int, list: List[Int]): Int = {
    list(n)
  }

  @scala.annotation.tailrec
  final def nth2(n: Int, list: List[Int]): Int = {
    n match {
      case _ if (n == 0) => list.head
      case _ if (n > 0) => nth2(n - 1, list.tail)
      case _ => throw new IllegalArgumentException
    }
  }

  def length(list: List[Int]): Int = {
    list.length
  }

  def length2(list: List[Int]): Int = {
    @scala.annotation.tailrec
    def length0(acc: Int, list: List[Int]): Int = {
      list match {
        case head :: Nil => acc + 1
        case head :: tail => length0(acc + 1, tail)
        case _ => throw new IllegalArgumentException
      }
    }
    length0(0, list)
  }


  def reverse(list: List[Int]): List[Int] = {
    list.reverse
  }

  def reverse2(list: List[Int]): List[Int] = {
    @scala.annotation.tailrec
    def reverse0(acc: List[Int], list: List[Int]): List[Int] = {
      list match {
        case head :: Nil => head :: acc
        case head :: tail => reverse0(head :: acc, tail)
        case _ => throw new IllegalArgumentException
      }
    }
    reverse0(List.empty[Int], list)
  }


  def isPalindrome(list: List[Int]): Boolean = {
    list == list.reverse
  }


  def flatten(nested: List[Any]): List[Any] = {
    @scala.annotation.tailrec
    def flatten0(acc: List[Any], list: List[Any]): List[Any] = {
      list match {
        case Nil => acc.reverse
        case (x: List[_]) :: xs => flatten0(acc, x ::: xs)
        case (x: Any) :: xs => flatten0(x :: acc, xs)
        case _ => throw new IllegalArgumentException
      }
    }
    flatten0(List.empty[Any], nested)
  }

  def compress(list: List[Symbol]): List[Symbol] = {
    @scala.annotation.tailrec
    def compress0(acc: List[Symbol], ls: List[Symbol]): List[Symbol] = {
      ls match {
        case Nil => acc.reverse
        case x :: xs => compress0(x :: acc, xs.dropWhile(_ == x))
      }
    }
    compress0(List.empty[Symbol], list)
  }

  def pack(list: List[Symbol]): List[List[Symbol]] = {
    @scala.annotation.tailrec
    def pack0(acc: List[List[Symbol]], ls: List[Symbol]): List[List[Symbol]] = {
      ls match {
        case Nil => acc.reverse
        case x :: xs => pack0((x :: xs).takeWhile(_ == x) :: acc, xs.dropWhile(_ == x))
      }
    }
    pack0(List.empty[List[Symbol]], list)
  }

  def encode(list: List[Symbol]): List[(Int, Symbol)] = {
    @scala.annotation.tailrec
    def encode0(acc: List[(Int, Symbol)], ls: List[Symbol]): List[(Int, Symbol)] = {
      ls match {
        case Nil => acc.reverse
        case x :: xs => encode0(((x :: xs).prefixLength(_ == x), x) :: acc, xs.dropWhile(_ == x))
      }
    }
    encode0(List.empty[(Int, Symbol)], list)
  }

}