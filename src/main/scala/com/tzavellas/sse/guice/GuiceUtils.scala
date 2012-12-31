/* ------------------ sse-guice ------------------ *\
 * Licensed under the Apache License, Version 2.0. *
 * Author: Spiros Tzavellas                        *
\* ----------------------------------------------- */
package com.tzavellas.sse.guice

import com.google.inject.{TypeLiteral, Key}
import com.google.inject.util.Types

/**
 * Various utility methods for working with Guice.
 */
object GuiceUtils {

  /** Create a `TypeLiteral` from a Scala `Manifest`. */
  def typeLiteralOf[A](implicit a: Manifest[A]): TypeLiteral[A] = {
    val targs = a.typeArguments.map(_.runtimeClass)
    TypeLiteral.get(Types.newParameterizedType(a.runtimeClass, targs:_*)).asInstanceOf[TypeLiteral[A]]
  }
  
  /** Create a `Key` from a Scala `Manifest`. */
  def keyOf[A](implicit a: Manifest[A]) = Key.get(typeLiteralOf(a))
}