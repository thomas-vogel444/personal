package canonical.kafka

import java.util.Properties
import java.util.concurrent.TimeUnit

import canonical.kafka.serialisation.PersonRecord.personFormat
import canonical.kafka.serialisation.{PersonRecord, Utils}
import org.apache.kafka.common.serialization.{Serde, Serdes}
import org.apache.kafka.streams.kstream.{Consumed, Produced}
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.KStream
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}

import scala.collection.JavaConverters._

object KafkaStreamMain extends App with Config {

  val props: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, "test-application")
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    p
  }

  val serdeProps = Map[String, Object](
    "schema.registry.url" -> "http://localhost:8081"
  )

  val personSerde: Serde[PersonRecord] = Utils.serde[PersonRecord](serdeProps.asJava, false)

  val builder = new StreamsBuilder
  val stream: KStream[String, PersonRecord] =
    builder.stream(inputTopic)(Consumed.`with`(Serdes.String, personSerde))

  stream.to(outputTopic)(Produced.`with`(Serdes.String, personSerde))

  val kafkaStream = new KafkaStreams(builder.build(), props)

  kafkaStream.start()

  sys.ShutdownHookThread {
    kafkaStream.close(10, TimeUnit.SECONDS)
  }
}
