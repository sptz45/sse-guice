/* ------------------ sse-guice ------------------ *\
 * Licensed under the Apache License, Version 2.0. *
 * Author: Spiros Tzavellas                        *
\* ----------------------------------------------- */
package com.tzavellas.sse.guice.binder

import java.lang.annotation.Annotation
import com.google.inject.binder.AnnotatedBindingBuilder
import com.google.inject.name.Names

class RichAnnotatedBindingBuilder[T](val builder: AnnotatedBindingBuilder[T])
  extends AnnotatedBindingBuilder[T]
     with RichLinkedBindingBuilder[T] {

  def annotatedWith(annotation: Annotation): RichLinkedBindingBuilder[T] = {
    builder.annotatedWith(annotation)
    this
  }
  
  def annotatedWith(annotationType: Class[_ <: Annotation]): RichLinkedBindingBuilder[T] = {
    builder.annotatedWith(annotationType)
    this
  }
  
  def annotatedWith[A <: Annotation](implicit a: Manifest[A]): RichLinkedBindingBuilder[T] = {
    builder.annotatedWith(a.erasure.asInstanceOf[Class[A]])
    this
  }
  
  def annotatedWithName(name: String): RichLinkedBindingBuilder[T] = {
    builder.annotatedWith(Names.named(name))
    this
  }
}