/* ------------------ sse-guice ------------------ *\
 * Licensed under the Apache License, Version 2.0. *
 * Author: Spiros Tzavellas                        *
\* ----------------------------------------------- */
package com.tzavellas.sse.guice

import com.google.inject.{TypeLiteral, Key}
import com.google.inject.util.Types

object Helpers {

  def typeLiteral[A](implicit a: Manifest[A]): TypeLiteral[A] = {
    val targs = a.typeArguments.map(_.erasure)
    TypeLiteral.get(Types.newParameterizedType(a.erasure, targs:_*)).asInstanceOf[TypeLiteral[A]]
  }
  
  def key[A](implicit a: Manifest[A]) = Key.get(typeLiteral(a))
}