package com.github.tamaki.study.tamaki.btree

/**
 * Created by shintaro.tamaki on 2015/06/25.
 */
/**
 * Node
 */
trait Node {
  def value: Int
  def size: Int
  def max: Int
  def min: Int
  def sum: Int
  def avg: Double

  def find(in: Int): Option[Node]
}

/**
 * 枝
 *
 * @param left　左の[[Node]]
 * @param value 値
 * @param right 右の[[Node]]
 */
case class Branch(left: Node, value: Int, right: Node) extends Node {
  def size: Int = left.size + 1 + right.size

  def sum: Int = left.sum + value + right.sum

  def avg: Double = sum.toDouble / size.toDouble

  def max: Int = right.max
  def max1: Int = List(left.max, value, right.max).max

  def min: Int = left.min
  def min1: Int = List(left.min, value, right.min).min

  def find(in: Int): Option[Node] = {
    (value == in) match {
      case true => Some(this)
      case false if (in < value) => left.find(in)
      case false if (in > value) => right.find(in)
    }
  }
}

/**
 * 葉を表す[[Node]]。
 *
 * @param value 値
 */
case class Leaf(value: Int) extends Node {
  def size: Int = 1
  def max: Int = value
  def min: Int = value
  def sum: Int = value
  def avg: Double = value.toDouble

  def find(in: Int): Option[Node] = {
    if (value == in) {
      Some(this)
    }
    else {
      None
    }
  }
}

/**
 * 二分木データ構造。
 *
 * @param node 頂点のノード
 */
case class BTree(node: Node) {
  def size: Int = node.size
  def max: Int = node.max
  def min: Int = node.min
  def sum: Int = node.sum
  def avg: Double = node.avg

  def find(in: Int): Option[Node] = node.find(in)
}

/**
 * コンパニオンオブジェクト
 */
object BTree {
}