package canonical.kafka.serialisation

import com.sksamuel.avro4s.RecordFormat

case class PersonRecord(
  name: String,
  age: Int,
  address: String = "No Address",
  retirementNumber: Option[String] = None,
  favouriteAnimal: FavouriteAnimal = Dog
)

sealed trait FavouriteAnimal
case object Cat extends FavouriteAnimal
case object Dog extends FavouriteAnimal
case object Fox extends FavouriteAnimal

object PersonRecord {
  implicit lazy val personFormat: RecordFormat[PersonRecord] = RecordFormat[PersonRecord]
}
