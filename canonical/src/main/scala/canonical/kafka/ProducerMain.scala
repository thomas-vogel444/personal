package canonical.kafka

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import scala.collection.JavaConverters._

object ProducerMain extends App {

  val topic = "kafka-test-topic"

  val config = Map[String, Object](
    "bootstrap.servers" -> "localhost:9092"
  )

  val kafkaProducer: KafkaProducer[String, String] = new KafkaProducer[String, String](config.asJava, new StringSerializer, new StringSerializer)

  kafkaProducer.send(new ProducerRecord(topic, "key1", "value1"))
  kafkaProducer.send(new ProducerRecord(topic, "key2", "value2"))
  kafkaProducer.send(new ProducerRecord(topic, "key3", "value3"))
  kafkaProducer.flush()
}
