package com.tzavellas.sse.guice

import com.google.inject._
import com.google.inject.binder.AnnotatedBindingBuilder
import binder._

abstract class ScalaModule extends AbstractModule {

  def bind[A](implicit a: Manifest[A]): RichAnnotatedBindingBuilder[A] = {
    val builder = a.typeArguments match {
      case Nil => bind(a.erasure)
      case _   => bind(Helpers.typeLiteral(a))
    }
    new RichAnnotatedBindingBuilder(builder.asInstanceOf[AnnotatedBindingBuilder[A]])
  }
  
  override def bindConstant() = new RichAnnotatedConstantBindingBuilder(super.bindConstant)
}
