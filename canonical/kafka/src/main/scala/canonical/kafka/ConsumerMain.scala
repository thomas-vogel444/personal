package canonical.kafka

import java.time.Duration
import java.util.Collections

import canonical.kafka.serialisation.{PersonRecord, TopicRecord}
import canonical.kafka.serialisation.AvroSerde._
import canonical.kafka.serialisation.MultiSchemaSerde.avroDeserializer
import canonical.kafka.serialisation.Records.personFormat
import io.confluent.kafka.serializers.subject.TopicRecordNameStrategy
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer

import scala.collection.JavaConverters._

object ConsumerMain extends App with Config {

  val consumerConfig = Map[String, AnyRef](
    "bootstrap.servers" -> "localhost:9092",
    "group.id" -> "consumer-example",
    "auto.offset.reset" -> "earliest",
    "enable.auto.commit" -> "true"
  )

  val deserializerConfig = Map[String, Object](
    "schema.registry.url" -> "http://localhost:8081",
    "value.subject.name.strategy" -> classOf[TopicRecordNameStrategy]
  )
  val multiDeserializer = avroDeserializer(deserializerConfig.asJava, false)

  val kafkaConsumer: KafkaConsumer[String, TopicRecord] =
    new KafkaConsumer[String, TopicRecord](consumerConfig.asJava, new StringDeserializer, multiDeserializer)

  kafkaConsumer.subscribe(Collections.singleton(inputTopic))

  kafkaConsumer
    .poll(Duration.ofSeconds(1)).iterator.asScala.toList
    .foreach { consumerRecord =>
      println(s"key: ${consumerRecord.key()}, value: ${consumerRecord.value()}")
    }

  kafkaConsumer.commitSync()
  multiDeserializer.close()
  kafkaConsumer.close()
}
