package exploding.domain

import scala.util.Random

case class CardDeck(cards: List[Card]) {
  def draw: (CardDeck, Option[Card]) =
    cards match {
      case Nil => (CardDeck(Nil), None)
      case card :: remaining => (CardDeck(remaining), Some(card))
    }

  def shuffle: CardDeck =
    CardDeck(Random.shuffle(cards))

  def topWith(card: Card): CardDeck =
    CardDeck(card :: cards)
}
