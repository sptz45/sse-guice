/* ------------------ sse-guice ------------------ *\
 * Licensed under the Apache License, Version 2.0. *
 * Author: Spiros Tzavellas                        *
\* ----------------------------------------------- */
package com.tzavellas.sse.guice
package binder

import javax.inject.{Provider => JProvider}

import com.google.inject._
import com.google.inject.binder.{LinkedBindingBuilder, ScopedBindingBuilder}
import java.lang.reflect.Constructor

trait RichLinkedBindingBuilder[T] extends LinkedBindingBuilder[T]
                                     with RichScopedBindingBuilder {

  val builder: LinkedBindingBuilder[T]
  
  def to(implementation: Class[_ <: T]): RichScopedBindingBuilder = {
    builder.to(implementation)
    this
  }
  
  def to(targetKey: Key[_ <: T]): RichScopedBindingBuilder = {
    builder.to(targetKey)
    this
  }
  
  def to(implementation: TypeLiteral[_ <: T]): RichScopedBindingBuilder = {
    builder.to(implementation)
    this
  }
  
  def to[I <:T](implicit i: Manifest[I]): RichScopedBindingBuilder = {
    if (i.typeArguments.isEmpty) {
      builder.to(i.runtimeClass.asInstanceOf[Class[I]])
    } else {
      builder.to(GuiceUtils.typeLiteralOf(i))
    }
    this
  }
  
  def toInstance(instance: T): Unit = {
    builder.toInstance(instance)
  }
  
  def toProvider(providerType: Class[_ <: JProvider[_ <: T]]): RichScopedBindingBuilder = {
    builder.toProvider(providerType)
    this
  }
  
  def toProvider(providerKey: Key[_ <: JProvider[_ <: T]]): RichScopedBindingBuilder = {
    builder.toProvider(providerKey)
    this
  }
  
  def toProvider(provider: Provider[_ <: T]): RichScopedBindingBuilder = {
    builder.toProvider(provider)
    this
  }
  
  def toProvider[P <: Provider[_ <: T]](implicit p: Manifest[P]): RichScopedBindingBuilder = {
    if (p.typeArguments.isEmpty) {
      builder.toProvider(p.runtimeClass.asInstanceOf[Class[P]])
    } else {
      builder.toProvider(GuiceUtils.typeLiteralOf(p))
    }
    this
  }

  def toProvider(typeLiteral: TypeLiteral[_ <: JProvider[_ <: T]]) : RichScopedBindingBuilder = {
    builder.toProvider(typeLiteral)
    this
  }

  def toProvider(provider: JProvider[_ <: T]): ScopedBindingBuilder = {
    builder.toProvider(provider)
    this
  }

  def toConstructor[S <: T](constructor: Constructor[S]): RichScopedBindingBuilder = {
    builder.toConstructor(constructor)
    this
  }

  def toConstructor[S <: T](constructor: Constructor[S], typeLiteral: TypeLiteral[_ <: S]): RichScopedBindingBuilder = {
    builder.toConstructor(constructor, typeLiteral)
    this
  }

  def toConstructor[I <:T](constructor: Constructor[I])(implicit i: Manifest[I]): RichScopedBindingBuilder = {
    builder.toConstructor(constructor, GuiceUtils.typeLiteralOf(i))
    this
  }
}