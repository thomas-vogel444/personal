import Dependencies._

ThisBuild / scalaVersion     := "2.13.3"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val `root` =
  (project in file("."))
    .aggregate(`api`, `flows`, `common`)

lazy val `api` = project.in(file("api"))
  .settings(
    name := "api",
    libraryDependencies += scalaTest % Test
  )

lazy val `flows` = project.in(file("flows"))
  .settings(
    name := "flows",
    libraryDependencies += scalaTest % Test
  )

lazy val `common` = project.in(file("common"))
  .settings(
    name := "common",
    libraryDependencies += scalaTest % Test
  )