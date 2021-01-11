package canonical.kafka.serialisation

import canonical.kafka.serialisation.Records.{employeeFormat, personFormat}
import com.sksamuel.avro4s.{Record, RecordFormat}
import io.confluent.kafka.serializers.{KafkaAvroDeserializer, KafkaAvroSerializer}
import org.apache.avro.generic.IndexedRecord
import org.apache.kafka.common.serialization.{Deserializer, Serializer}

object MultiSchemaSerde {

  def avroSerializer(configs: java.util.Map[String, _], isKey: Boolean): Serializer[TopicRecord] = new Serializer[TopicRecord] {
    val inner = new KafkaAvroSerializer()
    inner.configure(configs, isKey)

    override def serialize(topic: String, data: TopicRecord): Array[Byte] = {
      val record: Record = data match {
        case p: PersonRecord => Records.personFormat.to(p)
        case e: EmployeeRecord => Records.employeeFormat.to(e)
      }

      inner.serialize(topic, record)
    }

    override def configure(configs: java.util.Map[String, _], isKey: Boolean): Unit = ()
    override def close(): Unit = inner.close()
  }

  def avroDeserializer(configs: java.util.Map[String, _], isKey: Boolean): Deserializer[TopicRecord] = new Deserializer[TopicRecord] {
    val inner = new KafkaAvroDeserializer()
    inner.configure(configs, isKey)

    override def deserialize(topic: String, data: Array[Byte]): TopicRecord = {
      val record = inner.deserialize(topic, data).asInstanceOf[IndexedRecord]

      println(record.getSchema.getFullName)
      println(Records.personSchemaFor)
      println(Records.employeeSchemaFor)

      record.getSchema.getFullName match {
        case s if s == Records.personSchemaFor.getFullName => Records.personFormat.from(record)
        case s if s == Records.employeeSchemaFor.getFullName => Records.employeeFormat.from(record)
      }
    }

    override def configure(configs: java.util.Map[String, _], isKey: Boolean): Unit = ()
    override def close(): Unit = inner.close()
  }
}
