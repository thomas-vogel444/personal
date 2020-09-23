package exploding.domain

import exploding.Fixtures
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class CardDeckTest extends WordSpec with Matchers with ScalaCheckDrivenPropertyChecks with Fixtures {

  "ExplodingGame.drawCard" should {
    "return None if there are no card left" in {
      val (cardDeck, cardDrawn) = emptyCardDeck.draw

      cardDeck.cards shouldBe List.empty[Card]
      cardDrawn shouldBe None
    }

    "return the top card when the deck is non empty" in {
      forAll(cardGen, cardDeckGen) { (card, randomDeck) =>
        val (remainingDeck, cardDrawn) = randomDeck.topWith(card).draw

        remainingDeck.cards shouldBe randomDeck.cards
        cardDrawn shouldBe Some(card)
      }
    }
  }


}
