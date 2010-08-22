/* ------------------ sse-guice ------------------ *\
 * Licensed under the Apache License, Version 2.0. *
 * Author: Spiros Tzavellas                        *
\* ----------------------------------------------- */
package com.tzavellas.sse.guice

import org.junit.Test
import org.junit.Assert._
import com.google.inject._
import com.google.inject.name._
import Helpers._

class ScalaModuleTest {
  
  val in = Guice.createInjector(new SampleModule)
  
  @Test
  def test_for_smoke() {
    assertEquals("holder", in.getInstance(key[Holder[String]]).value)
    assertEquals("provider", in.getInstance(classOf[String]))
    assertEquals("21", in.getInstance(classOf[StringUtils]).reverse("12"))
  }
}

class SampleModule extends ScalaModule {
  
  def configure() {

    bind[Holder[String]].to[StringHolder].in[Singleton]
    bindConstant.annotatedWithName("holderValue").to("holder")
    
    bind[String].toProvider[StringProvider]
    
    bind[String => String].toInstance(x => x.reverse)
    bind[StringUtils]
  }
}

class StringUtils @Inject() (val reverse: String => String)

class StringProvider extends Provider[String] {
  def get = "provider"
}

trait Holder[T] { def value: T }

class StringHolder @Inject() (@Named("holderValue") val value: String)
  extends Holder[String]