/* ------------------ sse-guice ------------------ *\
 * Licensed under the Apache License, Version 2.0. *
 * Author: Spiros Tzavellas                        *
\* ----------------------------------------------- */
package com.tzavellas.sse.guice

import com.google.inject._
import com.google.inject.binder._
import binder._

abstract class ScalaModule extends AbstractModule {

  protected def bind[T](implicit m: Manifest[T]): RichAnnotatedBindingBuilder[T] = {
    m.typeArguments match {
      case Nil => bind(m.runtimeClass.asInstanceOf[Class[T]])
      case _   => bind(Helpers.typeLiteral(m))
    }
  }
  
  protected override def bind[T](clazz: Class[T]) = new RichAnnotatedBindingBuilder(super.bind(clazz))
  
  protected override def bind[T](key: Key[T]): RichLinkedBindingBuilder[T] =
    new RichLinkedBindingBuilderImpl(super.bind(key))
  
  
  protected override def bind[T](typeLiteral: TypeLiteral[T]) =
    new RichAnnotatedBindingBuilder(super.bind(typeLiteral))
  
  protected override def bindConstant() = new RichAnnotatedConstantBindingBuilder(super.bindConstant)
}

private class RichLinkedBindingBuilderImpl[T](val builder: LinkedBindingBuilder[T])
extends RichLinkedBindingBuilder[T]
