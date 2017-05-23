
lazy val root = (project in file(".")) dependsOn ssePlugin
lazy val ssePlugin = ProjectRef(file("../sbt-common"), "sbt-common")
addSbtPlugin("com.tzavellas" % "sse-sbt-common" % "0.1")
