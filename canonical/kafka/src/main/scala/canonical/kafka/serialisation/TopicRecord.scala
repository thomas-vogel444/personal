package canonical.kafka.serialisation

import com.sksamuel.avro4s.{AvroSchema, RecordFormat, SchemaFor}
import org.apache.avro.Schema

sealed trait TopicRecord
case class PersonRecord(
  name: String,
  age: Int,
  address: String = "No Address",
  retirementNumber: Option[String] = None,
  favouriteAnimal: FavouriteAnimal = Dog
) extends TopicRecord

sealed trait FavouriteAnimal
case object Cat extends FavouriteAnimal
case object Dog extends FavouriteAnimal
case object Fox extends FavouriteAnimal

case class EmployeeRecord(occupation: String, salary: Int) extends TopicRecord

object Records {
  implicit lazy val personFormat: RecordFormat[PersonRecord] = RecordFormat[PersonRecord]
  implicit lazy val employeeFormat: RecordFormat[EmployeeRecord] = RecordFormat[EmployeeRecord]

  implicit val personSchemaFor: Schema = AvroSchema[PersonRecord]
  implicit val employeeSchemaFor: Schema = AvroSchema[EmployeeRecord]
}