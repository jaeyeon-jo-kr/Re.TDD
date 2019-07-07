package jocatelo.tdd.ladder

enum class LadderPart(val block: Char = ' ')
{
    DOWN('│'),
    TURN_RIGHT('├'),
    TURN_LEFT('┤'),
    HORIZONTAL('─'),
    BLANK(' ')
}
