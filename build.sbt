import sbtrelease._
import ReleaseStateTransformations._

name := "sse-guice"
organization := "com.tzavellas"

description := "Make the Guice EDSL more Scala friendly"
homepage := Some(url("http://www.github.com/sptz45/sse-guice"))
startYear := Some(2010)
organizationName := "spiros.blog()"
organizationHomepage := Some(url("http://www.tzavellas.com"))
licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

scmInfo := Some(
  ScmInfo(
    url("http://github.com/sptz45/sse-guice/"),
    "scm:git:git://github.com/sptz45/sse-guice.git",
    Some("scm:git:git@github.com:sptz45/sse-guice.git")
  )
)

crossScalaVersions := Seq("2.10.4", "2.11.0", "2.12.0")
scalaVersion := "2.12.0"

libraryDependencies ++= Seq(
  "com.google.inject" % "guice"           % "4.1.0",
  "junit"             % "junit"           % "4.12" % "test",
  "com.novocode"      % "junit-interface" % "0.11" % "test"
)

scalacOptions ++= List("-feature", "-unchecked", "-deprecation", "-encoding", "UTF-8")
testOptions += Tests.Argument(TestFrameworks.JUnit, "-q")

jacoco.settings

publishMavenStyle := true
publishArtifact in Test := false

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <issueManagement>
    <system>GitHub</system>
    <url>http://github.com/sptz45/sse-guice/issues</url>
  </issueManagement>
  <developers>
    <developer>
      <id>sptz45</id>
      <name>Spiros Tzavellas</name>
      <email>spiros at tzavellas dot com</email>
      <url>http://www.tzavellas.com</url>
      <timezone>+2</timezone>
      <roles>
        <role>developer</role>
      </roles>
    </developer>
  </developers>
)

releaseSettings

ReleaseKeys.publishArtifactsAction := PgpKeys.publishSigned.value
ReleaseKeys.crossBuild := true

ReleaseKeys.releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  setNextVersion,
  commitNextVersion
)
