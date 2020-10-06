package kafkaexample.producer

import java.util.Properties

import kafkaexample.domain.Person
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.{Serializer, StringDeserializer, StringSerializer}
import io.circe.Encoder
import io.circe.generic.auto._
import org.apache.kafka.common.serialization.LongSerializer
import org.apache.kafka.common.serialization.StringSerializer

import scala.util.Try

class CirceKafkaSerializer[T](implicit encoder: Encoder[T]) extends Serializer[T] {
  override def serialize(topic: String, data: T): Array[Byte] =
  encoder(data).noSpaces.getBytes
}

object Producer {
  val personSerializer = new CirceKafkaSerializer[Person]()
  val props = new Properties()

  val TOPIC = "person"
  val BOOTSTRAP_SERVERS = "localhost:9092"

  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS)
  props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer")
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[CirceKafkaSerializer[Person]].getName)

  val producer = new KafkaProducer[String, Person](props, new StringSerializer, personSerializer)
}
