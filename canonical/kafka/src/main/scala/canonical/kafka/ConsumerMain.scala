package canonical.kafka

import java.time.Duration
import java.util.Collections

import canonical.kafka.serialisation.PersonRecord
import canonical.kafka.serialisation.Utils._
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer

import scala.collection.JavaConverters._

object ConsumerMain extends App {

  val topic = "kafka-test-topic"

  val consumerConfig = Map[String, AnyRef](
    "bootstrap.servers" -> "localhost:9092",
    "group.id" -> "consumer-example",
    "auto.offset.reset" -> "earliest",
    "enable.auto.commit" -> "true"
  )

  val deserializerConfig = Map[String, Object]("schema.registry.url" -> "http://localhost:8081")
  val personDeserializer = reflectionAvroDeserializer4S[PersonRecord](deserializerConfig.asJava, false)

  val kafkaConsumer: KafkaConsumer[String, PersonRecord] =
    new KafkaConsumer[String, PersonRecord](consumerConfig.asJava, new StringDeserializer, personDeserializer)

  kafkaConsumer.subscribe(Collections.singleton(topic))

  kafkaConsumer
    .poll(Duration.ofSeconds(1)).iterator.asScala.toList
    .foreach { consumerRecord =>
      println(s"key: ${consumerRecord.key()}, value: ${consumerRecord.value()}")
    }

  kafkaConsumer.commitSync()
  personDeserializer.close()
  kafkaConsumer.close()
}
