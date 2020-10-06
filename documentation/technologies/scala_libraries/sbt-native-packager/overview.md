# Overview

From https://www.freecodecamp.org/news/how-to-dockerise-a-scala-and-akka-http-application-the-easy-way-23310fc880fa/

## Setup

Add the plugin to your `project/plugin.sbt` file
```scala
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.7.5")
```

Enable the plugin in your `build.sbt` file
```scala
enablePlugins(JavaAppPackaging)
```

## Commands

Generating the Dockerfile
- run `docker:stage`
- The Dockerfile is now in `target/docker/stage/Dockerfile`

Building a Docker image
- run `sbt docker:publishLocal`

## Customizing the setup

From https://sbt-native-packager.readthedocs.io/en/stable/formats/docker.html

```scala
// Informational settings
packageName in Docker := "myPackage"
version in Docker := "1.3.2"
maintainer in Docker := "Thomas Vogel"

// Environment settings
dockerBaseImage := "openjdk:8-jre-alpine3.9"
daemonUser in Docker := "some-user" // User to use when executing the application
dockerExposedPorts := Seq(8080)

// Publishing settings
dockerRepository := "hub.docker.com:80" // repository to which the image is pushed when running docker:publish. Should be of the form [host[:port]] 
dockerUsername := "thomasvogel01"
dockerAlias := "" // The alias to be used for tagging the resulting image of the Docker build. Defaults to [dockerRepository/][dockerUsername/][packageName]:[version]
```

## Tasks

```sh
sbt docker:stage
sbt docker:publishLocal
sbt docker:publish # Need to first run 'docker login'
sbt:clean # removes the build image from the local Docker server
```