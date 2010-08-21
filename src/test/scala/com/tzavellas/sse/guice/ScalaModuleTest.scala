package com.tzavellas.sse.guice

import org.junit.Test
import com.google.inject._
import Helpers._

class ScalaModuleTest {
  
  val injector = Guice.createInjector(new SampleModule)
  
  @Test
  def test_for_smoke() {
    injector.getInstance(key[Holder[String]])
    injector.getInstance(key[String])
  }
}

class SampleModule extends ScalaModule {
  
  import com.google.inject.name._
  
  def configure() {

    //bind[String].annotatedWith[Named].to[String].in[Singleton]
    
    //bind[Holder[String]].toInstance(new StringHolder)
    
    bind[Holder[String]].to[StringHolder]
    
    //bind[String].toInstance("lala")
    bind[String].toProvider[StringProvider].in[Singleton]
  }
}

class StringProvider extends Provider[String] {
  def get = ""
}

trait Holder[T] { def value: T }
class StringHolder extends Holder[String] { def value = "hello" }