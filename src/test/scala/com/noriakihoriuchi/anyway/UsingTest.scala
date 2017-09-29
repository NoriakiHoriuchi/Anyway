package com.noriakihoriuchi.anyway

import java.io.{IOException, InputStream}
import java.util.concurrent.Executors

import org.scalatest.FlatSpec

class UsingTest extends FlatSpec {

  "Closeable" should "be closed" in {
    val is = new InputStream() {
      var isClosed: Boolean = false

      override def available(): Int = {
        super.available()
        if (isClosed) {
          throw new IOException("already closed")
        } else 0
      }

      override def read(): Int = ??? // no need to implement

      override def close(): Unit = {
        super.close()
        isClosed = true
      }
    }

    assert(is.available() == 0)
    using(is)(_ => ())
    assertThrows[IOException](is.available())
  }

  "ExecutorService" should "be closed" in {
    val es = Executors.newCachedThreadPool()

    assert(!es.isTerminated)
    using(es)(_ => ())
    assert(es.isTerminated)
  }

  "class which has simply close method" should "be closed" in {
    class HasClose {
      var isClosed: Boolean = false

      def close(): Unit = { isClosed = true }
    }
    val instance = new HasClose()
    assert(!instance.isClosed)
    using(instance)(_ => ())
    assert(instance.isClosed)
  }
}
