package jocatelo.tdd

import jocatelo.tdd.ladder.LadderCreator

fun main() {

    val creator = LadderCreator(7, 7)
    val ladder  = creator.create()

    print(ladder)
}