package com.noriakihoriuchi

import java.io.Closeable
import java.util.concurrent.ExecutorService

import scala.language.reflectiveCalls

package object anyway {
  type Closer[R] = R => Unit

  def using[R, T](closeable: R)(f: R => T)(implicit closer: Closer[R]): T =
    try {
      f(closeable)
    } finally {
      closer(closeable)
    }

  object Closers {

    type CloseMethodHolder[A] = A {
      def close(): Unit
    }

    implicit def closeMethodHolderCloser[A]: Closer[CloseMethodHolder[A]] =
      _.close()

    implicit def closeableCloser: Closer[Closeable] = _.close()

    implicit def executorServiceCloser: Closer[ExecutorService] = _.shutdown()
  }
}
