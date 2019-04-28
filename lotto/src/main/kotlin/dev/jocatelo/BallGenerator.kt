package dev.jocatelo

import kotlin.random.Random

object BallGenerator {
    fun generateRandom():Ball=Random.nextInt(1,46)
    fun generate(number:Int):Ball = number

}