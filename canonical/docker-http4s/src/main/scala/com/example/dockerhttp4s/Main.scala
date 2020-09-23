package com.example.dockerhttp4s

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object Main extends IOApp {
  def run(args: List[String]) =
    Dockerhttp4sServer.stream[IO].compile.drain.as(ExitCode.Success)
}