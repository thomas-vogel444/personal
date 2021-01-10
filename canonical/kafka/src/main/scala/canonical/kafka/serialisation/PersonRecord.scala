package canonical.kafka.serialisation

import com.sksamuel.avro4s.RecordFormat

case class PersonRecord(name: String, age: Int, address: String = "No Address", retirementNumber: Option[String] = None)

object PersonRecord {
  implicit lazy val personFormat: RecordFormat[PersonRecord] = RecordFormat[PersonRecord]
}
