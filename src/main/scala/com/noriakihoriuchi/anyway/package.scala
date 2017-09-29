package com.noriakihoriuchi

import java.io.InputStream
import java.util.concurrent.ExecutorService

package object anyway {
  type Closer[R] = R => Unit

  def using[R, T](closeable: R)(f: R => T)(implicit closer: Closer[R]): T =
    try {
      f(closeable)
    } finally {
      closer(closeable)
    }

  object Closers {
    implicit def inputStreamCloser: Closer[InputStream] = _.close()

    implicit def executorServiceCloser: Closer[ExecutorService] = _.shutdown()
  }
}
