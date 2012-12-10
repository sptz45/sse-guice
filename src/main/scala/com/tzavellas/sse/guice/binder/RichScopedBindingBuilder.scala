/* ------------------ sse-guice ------------------ *\
 * Licensed under the Apache License, Version 2.0. *
 * Author: Spiros Tzavellas                        *
\* ----------------------------------------------- */
package com.tzavellas.sse.guice.binder

import java.lang.annotation.Annotation
import com.google.inject.Scope
import com.google.inject.binder.ScopedBindingBuilder

trait RichScopedBindingBuilder extends ScopedBindingBuilder {

  val builder: ScopedBindingBuilder
  
  def asEagerSingleton() {
    builder.asEagerSingleton()
  }
  
  def in(scopeAnnotation: Class[_ <: Annotation]) {
    builder.in(scopeAnnotation)
  }
  
  def in(scope: Scope) {
    builder.in(scope)
  }
  
  def in[A <: Annotation](implicit ann: Manifest[A]) {
    builder.in(ann.runtimeClass.asInstanceOf[Class[A]])
  }
}