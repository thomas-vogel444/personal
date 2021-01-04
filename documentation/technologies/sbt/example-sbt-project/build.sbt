import Dependencies._

ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(name := "example-sbt-project")
  .aggregate(moduleA, common)

lazy val moduleA = (project in file("module-a"))
  .settings(
    name := "module-a",
    libraryDependencies += scalaTest % Test
  ).dependsOn(common)

lazy val common = (project in file("common"))
  .settings(
    name := "common",
    libraryDependencies += scalaTest % Test
  )