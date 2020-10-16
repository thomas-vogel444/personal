package kafkaexample.producer

import io.circe.Encoder
import io.circe.generic.auto._
import kafkaexample.KafkaClientSettings
import kafkaexample.domain.Person
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig}
import org.apache.kafka.common.serialization.{Serializer, StringSerializer}

class CirceKafkaSerializer[T](implicit encoder: Encoder[T]) extends Serializer[T] {
  override def serialize(topic: String, data: T): Array[Byte] =
  encoder(data).noSpaces.getBytes
}

class PersonProducer(kafkaClientSettings: KafkaClientSettings) {
  val personSerializer = new CirceKafkaSerializer[Person]()
  val props = kafkaClientSettings.toClientProps

  props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer")
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[CirceKafkaSerializer[Person]].getName)

  val producer = new KafkaProducer[String, Person](props, new StringSerializer, personSerializer)
}