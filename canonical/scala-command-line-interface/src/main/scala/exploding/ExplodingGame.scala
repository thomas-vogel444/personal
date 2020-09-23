package exploding

import exploding.domain.{Blank, CardDeck, Defuse, Explosive, Hand}

sealed trait Result
case object Explosion extends Result
case object BlankDrawn extends Result
case object DefuseAdded extends Result
case object DefuseUsed extends Result
case object UserWins extends Result

class ExplodingGame {
  def drawCard(cardDeck: CardDeck, hand: Hand): (CardDeck, Hand, Result) = {
      cardDeck.draw match {
        case (newCardDeck, Some(Explosive)) =>
          hand.defuseCards match {
            case Nil => (newCardDeck, hand, Explosion)
            case _ :: remainingDefuse =>
              (newCardDeck.topWith(Explosive).shuffle, Hand(remainingDefuse), DefuseUsed)
          }
        case (newCardDeck, Some(Defuse)) => (newCardDeck, hand.addOne, DefuseAdded)
        case (newCardDeck, Some(Blank)) => (newCardDeck, hand, BlankDrawn)
        case (newCardDeck, None)        => (newCardDeck, hand, UserWins) // The user will never win but for completeness
      }
    }

}