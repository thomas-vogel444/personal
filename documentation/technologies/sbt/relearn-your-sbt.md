# Relearn your sbt

From https://kubuszok.com/2018/relearn-your-sbt/#multi-module-project

- The notations `version := "1.0.0"` and `dependencies += "org.typelevel" %% "cats-core" % "1.0.1"` return a `Setting` object.
- You define a queue of modifications to the build settings. If the same setting is defined, only the last will be used.

## Multibuild projects

To declare dependencies between projects:
```scala
lazy val moduleB = (project in file("module-b"))
  .settings(
    ...
  )
  .dependsOn(moduleA)
```

To aggregate projects:
```scala
lazy val root = (project in file("."))
  .settings(
    ...
  )
  .aggregate(moduleA)
```

Of note:
- Tests are aggregated. Calling `sbt test` will run all the tests in the projects aggregated with the root project.
- The test code is not shared by default. You need to add `.dependsOn(moduleA % "compile->compile;test->test")` to share test code.