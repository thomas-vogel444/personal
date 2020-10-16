import Dependencies._

ThisBuild / scalaVersion     := "2.13.3"

resolvers ++= Seq(
  "Confluent" at "https://packages.confluent.io/maven/"
)

lazy val root = (project in file("."))
  .settings(
    name := "kafka-sandbox",
    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.0",
      "org.apache.kafka" % "kafka-clients" % "5.5.1-ccs",
      "io.circe" %% "circe-core" % "0.12.3",
      "io.circe" %% "circe-generic" % "0.12.3",
      "io.circe" %% "circe-parser" % "0.12.3",
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "org.slf4j" % "log4j-over-slf4j" % "1.7.25",
      scalaTest % Test
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
