package canonical.common

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PersonTest extends AnyWordSpec with Matchers{
  "Person.greetings" should {
    "greet as appropriate" in {
      val person = Person("Thomas", 33)

      Person.greetings(person) shouldBe "Greetings, Thomas! You are 33"
    }
  }
}
