
# sse-guice

A library to make the *Guice* internal DSL more *Scala* friendly.

The motivation for this library is the fact that, although Scala provides lots
of syntactic ways to create internal DSLs, when working with the Guice internal
DSL the Java code is both sorter an more readable. The main reason for this is
the absence of class literals in Scala. Instead of writing `Service.class` in
Scala we write `classOf[Service]` which is longer and less readable.

To address this problem sse-guice extends the interfaces of Guice's internal
DSL (defined in `com.google.inject.binder`) by adding a method that takes a
`Manifest` for each method that takes `Class` as a parameter. This allows us to
write:

```scala
bind[Service].to[ServiceImpl].in[Singleton]
```

instead of:

```scala
bind(classOf[Service]).to(classOf[ServiceImpl]).in(classOf[Singleton])
```

which is what we have to write when using Guice in Scala.

An added benefit of `Manifest`s is that they not only allow us to get rid of
classes but also `TypeLiteral`s. To define a binding for a generic type in
Guice (because of erasure) we have to construct a `TypeLiteral`. For example
to bind `Validator[Registration]` to `RegistrationVSpec` we have to write:

```scala
bind(new TypeLiteral[Validator[Registration]] {}).to(classOf[RegistrationVSpec])
```

Because `Manifest` also has information about the type arguments of a type when
using the methods that take `Manifest` as an argument sse-guice is able to
detect if the type has type arguments and construct a `TypeLiteral`
automatically. This allows us to write:

```scala
bind[Validator[Registration]].to[RegistrationVSpec]
```

and avoid the explicit creation of the `TypeLiteral`.

## Setup

For *Scala 2.9.x* use:

```xml
<dependency>
  <groupId>com.tzavellas</groupId>
  <artifactId>sse-guice</artifactId>
  <version>0.6.1</version>
</dependency>
```

For *Scala 2.10.x* use:

```xml
<dependency>
  <groupId>com.tzavellas</groupId>
  <artifactId>sse-guice</artifactId>
  <version>0.7.1</version>
</dependency>
```

## Usage

To use this library your Guice modules must extend the `ScalaModule` abstract
class instead of the `AbstractModule`. The `ScalaModule` adds methods and
overrides any methods that return binder interfaces to return the enhanced
interfaces of this library.

```scala
class MyModule extends ScalaModule {
  def configure() { bind[Service].to[ServiceImpl].in[Singleton]
    bind[CreditCardPaymentService]
    bind[PaymentService].to[CreditCardPaymentService]
    bindConstant().annotatedWithName("port").to(8080)
  }
}
```


## License

Licensed under the Apache License, Version 2.0. See the LICENSE and NOTICE
files for more information.
