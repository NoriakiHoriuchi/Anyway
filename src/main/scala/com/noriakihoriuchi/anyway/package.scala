package com.noriakihoriuchi

import java.io.InputStream

package object anyway {
  def using[T](closeable: InputStream)(f: InputStream => T): T = try {
    f(closeable)
  } finally {
    closeable.close()
  }
}
