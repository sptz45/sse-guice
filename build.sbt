
name := "sse-guice"
description := "Make the Guice EDSL more Scala friendly"
startYear := Some(2010)

crossScalaVersions := Seq("2.11.8", "2.12.2")
releaseCrossBuild := true

libraryDependencies ++= Seq(
  "com.google.inject" % "guice"           % "4.1.0",
  "junit"             % "junit"           % "4.12" % "test",
  "com.novocode"      % "junit-interface" % "0.11" % "test"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q")
