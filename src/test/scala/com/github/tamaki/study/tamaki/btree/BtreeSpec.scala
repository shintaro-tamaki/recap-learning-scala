package com.github.tamaki.study.tamaki.btree

import org.scalatest._
/**
 * Created by shintaro.tamaki on 2015/06/30.
 */
class BTreeSpec extends FunSpec with Matchers {

  describe("BTree") {

    describe("Create BTree with Leaf and Branch combination") {
      val bTree1 = BTree(Leaf(1))
      val bTree2 = BTree(Branch(Leaf(1), 2, Leaf(3)))
      val bTree3 = BTree(Branch(Branch(Leaf(1), 2, Leaf(3)), 4, Branch(Leaf(5), 6, Leaf(7))))

      println("Btree")
      println(bTree1)
      println(bTree2)
      println(bTree3)
      println("")

      it("should compute size of BTree") {
        bTree1.size should be (1)
        bTree2.size should be (3)
        bTree3.size should be (7)
      }

      it("should compute max value of BTree") {
        bTree1.max should be (1)
        bTree2.max should be (3)
        bTree3.max should be (7)
      }

      it("should compute min value of BTree") {
        bTree1.min should be (1)
        bTree2.min should be (1)
        bTree3.min should be (1)
      }

      it("should compute sum value of BTree") {
        bTree1.sum should be (1)
        bTree2.sum should be (6)
        bTree3.sum should be (28)
      }

      it("should compute avg value of BTree") {
        bTree1.avg should be (1.toDouble)
        bTree2.avg should be (2.toDouble)
        bTree3.avg should be (4.toDouble)
      }

      it("should find value of BTree") {
        println("")
        println(bTree1.find(1))
        println(bTree2.find(2))
        println(bTree2.find(3))
        println(bTree3.find(4))
        println(bTree3.find(6))
        println(bTree3.find(7))
      }


      //
//      it("should create BTree from list") {
//        BTree(List(1)) should be (bTree1)
//        BTree(List(1, 2, 3)) should be (bTree2)
//        BTree(List(1, 2, 3, 4, 5, 6, 7)) should be (bTree3)
//      }
    }

  }
}