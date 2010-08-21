
# sse-guice

The aim of this library is to make the Guice EDSL more Scala friendly.


## Usage

	class MyModule extends ScalaModule {
	  def configure() {
	    bind[Service].to[ServiceImpl].in[Singleton]
		bind[CreditCardPaymentService]
		bind[PaymentService].to[CreditCardPaymentService]
	    bindConstant().annotatedWithName("port").to(8080)
	  }
	}
	
	class CoeusWebModule extends ScalaModule {
	  def configure() {
	    bind[Validator[Registration]].toInstance(new RegistrationVSpec)
	    bind[RegistrationController]
	  }
	}

## License

Licensed under the Apache License, Version 2.0. See the LICENSE and NOTICE
files for more information.
