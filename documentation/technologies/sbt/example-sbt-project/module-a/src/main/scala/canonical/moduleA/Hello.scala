package canonical.moduleA

import canonical.common.Person

object Hello extends App {
  val person = Person("Thomas", 33)

  println(Person.greetings(person))
}


