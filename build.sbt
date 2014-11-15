
name := "sse-guice"
organization := "com.tzavellas"
version := "0.7.2-SNAPSHOT"

crossScalaVersions := Seq("2.10.0", "2.11.0")
scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  "com.google.inject" % "guice"           % "3.0",
  "junit"             % "junit"           % "4.11" % "test",
  "com.novocode"      % "junit-interface" % "0.11" % "test"
)

scalacOptions ++= List("-feature", "-unchecked", "-deprecation", "-target:jvm-1.6", "-encoding", "UTF-8")

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q")

//scalariformSettings

jacoco.settings
