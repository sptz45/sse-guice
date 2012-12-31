/* ------------------ sse-guice ------------------ *\
 * Licensed under the Apache License, Version 2.0. *
 * Author: Spiros Tzavellas                        *
\* ----------------------------------------------- */
package com.tzavellas.sse

/**
 * A library to make the <i>Guice</i> internal DSL more <i>Scala</i> friendly.
 * 
 * ==Usage==
 * 
 * To use this library your Guice modules must extend the `ScalaModule` abstract
 * class instead of the `AbstractModule`. The `ScalaModule` adds methods and
 * overrides any methods that return binder interfaces to return the enhanced
 * interfaces of this library.
 * {{{
 * class MyModule extends ScalaModule {
 *   def configure() {
 *     bind[Service].to[ServiceImpl].in[Singleton]
 *     bind[CreditCardPaymentService]
 *     bind[PaymentService].to[CreditCardPaymentService]
 *     bindConstant().annotatedWithName("port").to(8080)
 *   }
 * }
 * }}} 
 */
package object guice {}