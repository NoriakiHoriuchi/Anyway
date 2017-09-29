package com.noriakihoriuchi.anyway

import java.io.{IOException, InputStream}

import org.scalatest.FlatSpec

class UsingTest extends FlatSpec {

  "InputStream" should "be closed" in {
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
}
