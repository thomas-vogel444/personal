package canonical.kafka

import java.time.Duration
import java.util.Collections

import org.apache.kafka.clients.consumer.{ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import scala.collection.JavaConverters._

object ConsumerMain extends App {

  val topic = "kafka-test-topic"

  val config = Map[String, AnyRef](
    "bootstrap.servers" -> "localhost:9092",
    "group.id" -> "consumer-example",
    "auto.offset.reset" -> "earliest",
    "enable.auto.commit" -> "true"
  )

  val kafkaConsumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](config.asJava, new StringDeserializer, new StringDeserializer)

  kafkaConsumer.subscribe(Collections.singleton(topic))

  kafkaConsumer
    .poll(Duration.ofSeconds(1)).iterator.asScala.toList
    .foreach { consumerRecord =>
      println(s"key: ${consumerRecord.key()}, value: ${consumerRecord.value()}")
    }
  
  kafkaConsumer.commitSync()
  kafkaConsumer.close()
}
