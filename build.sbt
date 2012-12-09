organization := "com.tzavellas"

name := "sse-guice"

version := "0.5"

scalaVersion := "2.10.0-RC1"

libraryDependencies ++= Seq(
  "com.google.inject" % "guice" % "3.0",
  "junit" % "junit" % "4.7" % "test"
)
