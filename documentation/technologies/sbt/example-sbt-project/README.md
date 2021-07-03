# Example SBT project

## Cheatsheet

To run tests in `common` only:
```sh
sbt "common/test"
```

To run all tests:
```
sbt test
```

To run moduleA Main class:
```sh
sbt "moduleA/run"
sbt "moduleA/runMain canonical.moduleA.Hello"
```