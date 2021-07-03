package canonical.common

case class Person(name: String, age: Int)

object Person extends App {
  println("You are calling common!")

  def greetings(person: Person): String =
    s"Greetings, ${person.name}! You are ${person.age}"
}
