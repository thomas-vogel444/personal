package kafkaexample.consumer

import java.util.Properties

import com.typesafe.config.Config
import io.circe.Decoder
import io.circe.generic.auto._
import io.circe.parser.decode
import kafkaexample.KafkaClientSettings
import kafkaexample.domain.Person
import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.{Deserializer, StringDeserializer}

class CirceKafkaDeserializer[T](implicit decoder: Decoder[T]) extends Deserializer[T]{
  override def deserialize(topic: String, data: Array[Byte]): T =
  decode[T](new String(data))
  .fold(error => throw error, identity)
}

class PersonConsumer(kafkaClientSettings: KafkaClientSettings) {
  val props = kafkaClientSettings.toClientProps
  props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer")
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[Person].getName)

  val personDeserializer = new CirceKafkaDeserializer[Person]()
  val consumer = new KafkaConsumer[String, Person](props, new StringDeserializer, personDeserializer)
}
