ThisBuild / scalaVersion     := "2.12.12"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

resolvers ++= Seq(
  "Confluent" at "https://packages.confluent.io/maven/",
  Resolver.bintrayRepo("cakesolutions", "maven")
)

lazy val root = (project in file("."))
  .settings(
    name := "canonical",
    libraryDependencies ++= Dependencies.all
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
