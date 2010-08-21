/* ------------------ sse-guice ------------------ *\
 * Licensed under the Apache License, Version 2.0. *
 * Author: Spiros Tzavellas                        *
\* ----------------------------------------------- */
package com.tzavellas.sse.guice
package binder

import com.google.inject._
import com.google.inject.binder.LinkedBindingBuilder

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
      builder.to(i.erasure.asInstanceOf[Class[I]])
    } else {
      builder.to(Helpers.typeLiteral(i))
    }
    this
  }
  
  def toInstance(instance: T) {
    builder.toInstance(instance)
  }
  
  def toProvider(providerType: Class[_ <: Provider[_ <: T]]): RichScopedBindingBuilder = {
    builder.toProvider(providerType)
    this
  }
  
  def toProvider(providerKey: Key[_ <: Provider[_ <: T]]): RichScopedBindingBuilder = {
    builder.toProvider(providerKey)
    this
  }
  
  def toProvider(provider: Provider[_ <: T]): RichScopedBindingBuilder = {
    builder.toProvider(provider)
    this
  }
  
  def toProvider[P <: Provider[_ <: T]](implicit p: Manifest[P]): RichScopedBindingBuilder = {
    builder.toProvider(p.erasure.asInstanceOf[Class[P]])
    this
  }
}