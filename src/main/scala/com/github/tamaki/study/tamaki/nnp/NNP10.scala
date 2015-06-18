package com.github.tamaki.study.tamaki.nnp

import scala.annotation.tailrec

/**
 * Created by tamaki on 2015/02/08.
 */
trait NNP10 {

  // P01 (*) Find the last element of a list.
  def last(list: List[Int]): Int = {
    list.last
  }

  @tailrec
  final def last2(list: List[Int]): Int = {
    list match {
      case head :: Nil => head
      case head :: tail => last2(tail)
      case _ => throw new IllegalArgumentException
    }
  }

  // P02 (*) Find the last but one element of a list.
  def penultimate(list: List[Int]): Int = {
    list.init.last
  }

  @tailrec
  final def penultimate2(list: List[Int]): Int = {
    list match {
      case head :: tail if tail.length > 2 => penultimate2(tail)
      case head :: tail if tail.length == 2 => tail.head
      case _ => throw new IllegalArgumentException
    }
  }


  def nth(n: Int, list: List[Int]): Int = {
    list(n)
  }

  //間違ってるっぽい
  @tailrec
  final def nth2(n: Int, list: List[Int]): Int = {
    list match {
      case head :: tail :: Nil => tail
      case head :: tail :: third => nth2(n, tail :: third)
      case _ => throw new IllegalArgumentException
    }
  }

  def length(list: List[Int]): Int = {
    list.length
  }

  def length2(list: List[Int]): Int = {
    @tailrec
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
    @tailrec
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

  def isPalindrome2(list: List[Int]): Boolean = {
    list == list.reverse
  }


  def flatten(nested: List[Any]): List[Any] = {
    ???
  }

  def compress(list: List[Symbol]): List[Symbol] = {
    ???
  }

  def pack(list: List[Symbol]): List[List[Symbol]] = {
    ???
  }

  def encode(list: List[Symbol]): List[(Int, Symbol)] = {
    ???
  }

}