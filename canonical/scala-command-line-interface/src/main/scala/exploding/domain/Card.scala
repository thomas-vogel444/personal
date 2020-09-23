package exploding.domain

sealed trait Card
case object Blank extends Card
case object Explosive extends Card
case object Defuse extends Card