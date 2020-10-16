package kafkaexample

import java.time.Duration
import java.util.Collections

import kafkaexample.consumer.PersonConsumer
import kafkaexample.domain.Person
import org.apache.kafka.clients.consumer.ConsumerRecords

import scala.jdk.CollectionConverters.IterableHasAsScala

object ConsumerMain extends App {

  val TOPIC = "test-topic"
  val consumer = new PersonConsumer(KafkaConfig.kafkaClientConfig).consumer

  consumer.subscribe(Collections.singleton(TOPIC))

  while (true) {
    val consumerRecords: ConsumerRecords[String, Person] = consumer.poll(Duration.ofSeconds(1))

    consumerRecords.records(TOPIC).asScala.toList
      .foreach(record => println(s"key: ${record.key}, value: ${record.value}"))
  }

}
