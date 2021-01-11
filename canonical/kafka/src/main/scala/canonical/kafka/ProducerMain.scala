package canonical.kafka

import canonical.kafka.serialisation.{Cat, Dog, EmployeeRecord, Fox, MultiSchemaSerde, PersonRecord, TopicRecord}
import canonical.kafka.serialisation.AvroSerde._
import io.confluent.kafka.serializers.subject.TopicRecordNameStrategy
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.{Serializer, StringSerializer}

import scala.collection.JavaConverters._

object ProducerMain extends App with Config {

  val producerConfig =
    Map[String, Object]("bootstrap.servers" -> "localhost:9092")

  val serializerConfig =
    Map[String, Object](
      "schema.registry.url" -> "http://localhost:8081",
        "value.subject.name.strategy" -> classOf[TopicRecordNameStrategy]
    )

  val multiSerialiser: Serializer[TopicRecord] = MultiSchemaSerde.avroSerializer(serializerConfig.asJava, false)

  val kafkaProducer: KafkaProducer[String, TopicRecord] =
    new KafkaProducer[String, TopicRecord](producerConfig.asJava, new StringSerializer, multiSerialiser)

  kafkaProducer.send(new ProducerRecord(inputTopic, "key1", PersonRecord("Thomas", 33, "1089 Greenford Road", None, Dog)))
  kafkaProducer.send(new ProducerRecord(inputTopic, "key2", PersonRecord("John", 68, "1089 Greenford Road", Some("123"), Cat)))
  kafkaProducer.send(new ProducerRecord(inputTopic, "key3", PersonRecord("Annie", 70, "somewhere else", Some("456"), Fox)))
  kafkaProducer.send(new ProducerRecord(inputTopic, "key4", EmployeeRecord("Software Dev", 75000)))
  kafkaProducer.send(new ProducerRecord(inputTopic, "key5", EmployeeRecord("Cleaner", 12)))
  kafkaProducer.flush()

  multiSerialiser.close()
  kafkaProducer.close()
}
