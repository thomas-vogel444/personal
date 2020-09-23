package exploding.domain

case class Hand(defuseCards: List[Defuse.type]) {
  def addOne: Hand =
    Hand(Defuse :: defuseCards)
}