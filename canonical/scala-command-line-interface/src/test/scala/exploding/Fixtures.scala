package exploding

import exploding.domain.{Blank, Card, CardDeck, Defuse, Explosive, Hand}
import org.scalacheck.Gen

import scala.util.Random

trait Fixtures {

  val emptyCardDeck = CardDeck(List.empty)
  val emptyCardHand = Hand(Nil)

  val cardGen: Gen[Card] =
    Gen.oneOf(Explosive, Blank)

  val blankCardDeckGen: Gen[CardDeck] =
    Gen.chooseNum(0, 12)
      .map(length => CardDeck(List.fill(length)(Blank)))

  val cardDeckGen: Gen[CardDeck] =
    Gen.chooseNum(0, 12)
      .map(length => CardDeck(Random.shuffle(Explosive :: List.fill(length)(Blank))))

  val handGen: Gen[Hand] =
    Gen.chooseNum(0, 3)
      .map(length => Hand(List.fill(length)(Defuse)))
}
