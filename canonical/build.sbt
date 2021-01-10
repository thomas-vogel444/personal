ThisBuild / scalaVersion     := "2.12.12"

lazy val `root` =
  (project in file("."))
    .aggregate(kafka)

lazy val kafka = (project in file("kafka"))
  .settings(
    resolvers ++= Seq("Confluent" at "https://packages.confluent.io/maven/"),
    libraryDependencies ++=
      KafkaDependencies.all ++
        OtherDependencies.all ++
        TestDependencies.all
  )