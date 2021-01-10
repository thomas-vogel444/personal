package canonical.kafka

import canonical.kafka.serialisation.{Cat, Dog, Fox, PersonRecord}
import canonical.kafka.serialisation.Utils._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import scala.collection.JavaConverters._

object ProducerMain extends App with Config {

  val producerConfig = Map[String, Object]("bootstrap.servers" -> "localhost:9092")
  val serializerConfig = Map[String, Object]("schema.registry.url" -> "http://localhost:8081")

  val personSerialiser = avroSerializerFor[PersonRecord](serializerConfig.asJava, false)

  val kafkaProducer: KafkaProducer[String, PersonRecord] =
    new KafkaProducer[String, PersonRecord](producerConfig.asJava, new StringSerializer, personSerialiser)

  kafkaProducer.send(new ProducerRecord(inputTopic, "key1", PersonRecord("Thomas", 33, "1089 Greenford Road", None, Dog)))
  kafkaProducer.send(new ProducerRecord(inputTopic, "key2", PersonRecord("John", 68, "1089 Greenford Road", Some("123"), Cat)))
  kafkaProducer.send(new ProducerRecord(inputTopic, "key3", PersonRecord("Annie", 70, "somewhere else", Some("456"), Fox)))
  kafkaProducer.flush()

  personSerialiser.close()
  kafkaProducer.close()
}
