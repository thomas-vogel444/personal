package kafkaexample

import kafkaexample.domain.Person
import kafkaexample.producer.Producer.{TOPIC, producer}
import org.apache.kafka.clients.producer.ProducerRecord

import scala.util.Try

object ProducerMain extends App {

  Try {
    val record = new ProducerRecord(TOPIC, "1", Person("Tom Vogel", 33))
    producer.send(record).get()
  }

  producer.flush()
  producer.close()
}
