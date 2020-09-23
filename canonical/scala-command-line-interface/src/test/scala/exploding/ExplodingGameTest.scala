package exploding

import exploding.domain.{Blank, Defuse, Explosive}
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class ExplodingGameTest extends WordSpec with Matchers with ScalaCheckDrivenPropertyChecks with Fixtures {
  val explodingGame = new ExplodingGame()

  "ExplodingGame.drawCard" should {
    "return UserWins when the card deck is empty" in {
      forAll(handGen) { hand =>
        val (_, _, result) = explodingGame.drawCard(emptyCardDeck, hand)

        result shouldBe UserWins
      }

    }

    "return BlankDrawn if the top card is a Blank card, remove the card and keep the rest of the deck as is" in {
      forAll(cardDeckGen suchThat(_.cards.nonEmpty), handGen) { (randomDeck, hand) =>
        val (remainingDeck, newHand, result) = explodingGame.drawCard(randomDeck.topWith(Blank), hand)

        result shouldBe BlankDrawn
        remainingDeck.cards  shouldBe randomDeck.cards
        newHand.defuseCards.length shouldBe hand.defuseCards.length
      }
    }

    "return Explosion if the top card is an Explosive card and the player doesn't have a Defuse card" in {
      forAll(cardDeckGen) { randomDeck =>
        val (_, _, result) = explodingGame.drawCard(randomDeck.topWith(Explosive), emptyCardHand)

        result shouldBe Explosion
      }
    }

    "return DefuseUsed, removes a Defuse, adds the Explosive back and shuffles the deck if the top card is an Explosive card and the player has a Defuse card" in {
      forAll(cardDeckGen, handGen suchThat(_.defuseCards.nonEmpty)) { (randomDeck, hand) =>
        val (newDeck, newHand, result) = explodingGame.drawCard(randomDeck.topWith(Explosive), hand)

        result shouldBe DefuseUsed
        newDeck.cards should contain (Explosive)
        newDeck.cards.length shouldBe randomDeck.cards.length + 1
        newHand.defuseCards.length shouldBe hand.defuseCards.length - 1
      }
    }

    "return DefuseAdded and adds a Defuse if the top card is a Defuse card" in {
      forAll(cardDeckGen, handGen) { (randomDeck, hand) =>
        val (_, newHand, result) = explodingGame.drawCard(randomDeck.topWith(Defuse), hand)

        result shouldBe DefuseAdded
        newHand.defuseCards.length shouldBe hand.defuseCards.length + 1
      }
    }
  }

}
