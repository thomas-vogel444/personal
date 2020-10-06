package kafkaexample

import java.time.Duration
import java.util.Collections

import kafkaexample.consumer.Consumer
import kafkaexample.domain.Person
import kafkaexample.producer.Producer.TOPIC
import org.apache.kafka.clients.consumer.ConsumerRecords

import scala.jdk.CollectionConverters.IterableHasAsScala

object ConsumerMain extends App {

  Consumer.consumer.subscribe(Collections.singleton(TOPIC))

  while (true) {
    val consumerRecords: ConsumerRecords[String, Person] = Consumer.consumer.poll(Duration.ofSeconds(1))

    consumerRecords.records(TOPIC).asScala.toList
      .foreach(record => println(s"key: ${record.key}, value: ${record.value}"))
  }

}
