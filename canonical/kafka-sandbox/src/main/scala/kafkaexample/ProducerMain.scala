package kafkaexample

import kafkaexample.domain.Person
import kafkaexample.producer.PersonProducer
import org.apache.kafka.clients.producer.ProducerRecord

import scala.util.Try

object ProducerMain extends App {
  val producer = new PersonProducer(KafkaConfig.kafkaClientConfig).producer
  val TOPIC = "test-topic"

  println(s"Config: ${KafkaConfig.kafkaClientConfig}")

  println("Sending a person record to Kafka")
  val sendingTry = Try {
    val record = new ProducerRecord(TOPIC, "1", Person("Tom Vogel", 33))
    producer.send(record).get()
  }
  producer.flush()

  sendingTry.fold(
    e => println(s"Error: ${e.getMessage}"),
    _ => println("It worked!")
  )

  println("Done producing records")
  producer.close()
}
