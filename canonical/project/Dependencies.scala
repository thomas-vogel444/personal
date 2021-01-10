import sbt._

object KafkaDependencies {
  val avro4sVersion = "1.9.0"
  val confluentVersion = "5.5.1"

  val all = Seq(
    "org.apache.kafka" % "kafka-clients" % (confluentVersion + "-ccs"),
    "io.confluent" % "kafka-schema-registry-client" % confluentVersion,
    "io.confluent" % "kafka-avro-serializer" % confluentVersion,
    "org.apache.kafka" %% "kafka-streams-scala" % "2.0.0",
    "com.sksamuel.avro4s" %% "avro4s-core" % "3.1.1"
  )
}

object OtherDependencies {
  val all = Seq(
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "com.github.pureconfig" %% "pureconfig" % "0.14.0"
  )
}

object TestDependencies {
  val all = Seq(
    "org.scalatest" %% "scalatest" % "3.2.2" % Test
  )
}