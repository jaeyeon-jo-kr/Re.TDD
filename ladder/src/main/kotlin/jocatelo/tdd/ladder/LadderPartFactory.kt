package jocatelo.tdd.ladder

object LadderPartFactory {
    fun createDown() = LadderPart.DOWN

    fun createTurnRight()= LadderPart.TURN_RIGHT

    fun createTurnLeft()= LadderPart.TURN_LEFT

    fun createHorizontal() = LadderPart.HORIZONTAL

    fun createBlank() = LadderPart.BLANK

}
