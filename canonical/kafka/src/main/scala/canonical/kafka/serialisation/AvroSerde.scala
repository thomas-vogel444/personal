package canonical.kafka.serialisation

import com.sksamuel.avro4s.RecordFormat
import io.confluent.kafka.serializers.{KafkaAvroDeserializer, KafkaAvroSerializer}
import org.apache.avro.generic.IndexedRecord
import org.apache.kafka.common.serialization.{Deserializer, Serde, Serializer, Serdes => JSerdes}

object AvroSerde {

  def avroSerializerFor[T: RecordFormat](configs: java.util.Map[String, _], isKey: Boolean): Serializer[T] = new Serializer[T] {
    val inner = new KafkaAvroSerializer()
    inner.configure(configs, isKey)

    override def serialize(topic: String, data: T): Array[Byte] =
      inner.serialize(topic, implicitly[RecordFormat[T]].to(data))

    override def configure(configs: java.util.Map[String, _], isKey: Boolean): Unit = ()
    override def close(): Unit = inner.close()
  }

  def avroDeserializerFor[T: RecordFormat](configs: java.util.Map[String, _], isKey: Boolean): Deserializer[T] = new Deserializer[T] {
    val inner = new KafkaAvroDeserializer()
    inner.configure(configs, isKey)

    override def deserialize(topic: String, data: Array[Byte]): T =
      implicitly[RecordFormat[T]]
        .from(inner.deserialize(topic, data).asInstanceOf[IndexedRecord])

    override def configure(configs: java.util.Map[String, _], isKey: Boolean): Unit = ()
    override def close(): Unit = inner.close()
  }

  def serde[T: RecordFormat](configs: java.util.Map[String, _], isKey: Boolean): Serde[T] = {
    JSerdes.serdeFrom(
      avroSerializerFor[T](configs, isKey),
      avroDeserializerFor[T](configs, isKey)
    )
  }

}
