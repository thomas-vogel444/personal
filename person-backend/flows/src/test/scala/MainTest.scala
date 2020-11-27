import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MainTest extends AnyWordSpec with Matchers {

  "Main" should {
    "get 3 + 3 = 6" in {
      3 + 3 shouldBe 6
    }
  }
}
