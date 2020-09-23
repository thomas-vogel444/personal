package exploding

import exploding.domain.{Blank, CardDeck, Defuse, Explosive, Hand}

import scala.io.StdIn

sealed trait Command
case object DrawNextCard extends Command

object ExplodingMain {

  val numberOfBlanks = 16
  val numberOfDefuses = 2

  def main(args: Array[String]): Unit = {
    println(
      """Welcome to Explode! You are about to embark on a fascinating game of
        |exploration into the unknown. You start with a Difuse in your hand. Press 'd' to draw a card...""".stripMargin)

    val initialDeck = CardDeck(Explosive :: List.fill(numberOfDefuses)(Defuse) ::: List.fill(numberOfBlanks)(Blank)).shuffle
    val initialHand = Hand(List(Defuse))

    val explodingGame = new ExplodingGame()

    play(initialDeck, initialHand)

    @scala.annotation.tailrec
    def getNextUserCommand: Command = {
      StdIn.readLine() match {
        case "d" =>
          DrawNextCard
        case _ =>
          println("Wrong input, mate. Try again...")
          getNextUserCommand
      }
    }

    @scala.annotation.tailrec
    def play(cardDeck: CardDeck, hand: Hand): Unit =
      getNextUserCommand match {
        case DrawNextCard =>
          explodingGame.drawCard(cardDeck, hand) match {
            case (newCardDeck, newHand, DefuseAdded) =>
              println(s"You pulled another Defuse which brings the number to ${newHand.defuseCards.length}! Gonna need it soon, I tell you...")
              play(newCardDeck, newHand)
            case (newCardDeck, newHand, DefuseUsed) =>
              println(s"That was close! You were going to explode but a difuse saved the day...  You only have ${newHand.defuseCards.length} left")
              play(newCardDeck, newHand)
            case (newCardDeck, newHand, BlankDrawn) =>
              println("You drew a blank. Lucky you... Draw another card if you dare!")
              play(newCardDeck, newHand)
            case (_, _, Explosion) =>
              println("Explosion! You lost, bro...")
            case (_, _, UserWins) =>
              println("No more cards! I guess you win...")
          }
      }
  }

}
