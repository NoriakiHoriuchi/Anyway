package com.noriakihoriuchi

import java.io.InputStream
import java.util.concurrent.ExecutorService

package object anyway {
  def using[R, T](closeable: R)(f: R => T)(implicit closer: R => Unit): T =
    try {
      f(closeable)
    } finally {
      closer(closeable)
    }

  object Closers {
    implicit def inputStreamCloser: InputStream => Unit = is => is.close()

    implicit def executorServiceCloser: ExecutorService => Unit =
      es => es.shutdown()
  }
}
