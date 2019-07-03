package jocatelo.tdd.ladder

object LadderPartFactory {
    fun createDown() = LadderPart('│')

    fun createTurnRight()= LadderPart('├')

    fun createTurnLeft()= LadderPart('┤')

    fun createHorizontal() = LadderPart('─')

    fun createBlank() = LadderPart(' ')

}
