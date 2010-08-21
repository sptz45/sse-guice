package com.tzavellas.sse.guice.binder

import com.google.inject.binder.{ConstantBindingBuilder, AnnotatedConstantBindingBuilder}
import com.google.inject.name.Names
import java.lang.annotation.Annotation

class RichAnnotatedConstantBindingBuilder(val builder: AnnotatedConstantBindingBuilder)
  extends AnnotatedConstantBindingBuilder {
  
  def annotatedWith(annotation: Annotation): ConstantBindingBuilder = {
    builder.annotatedWith(annotation)
  }
  
  def annotatedWith(annotationType: Class[_ <: Annotation]): ConstantBindingBuilder = {
    builder.annotatedWith(annotationType)
  }
  
  def annotatedWith[A <: Annotation](implicit a: Manifest[A]): ConstantBindingBuilder = {
    builder.annotatedWith(a.erasure.asInstanceOf[Class[A]])
  }
  
  def annotatedWithName(name: String): ConstantBindingBuilder = {
    builder.annotatedWith(Names.named(name))
  }
}