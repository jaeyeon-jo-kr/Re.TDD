package dev.jocatelo

object LottoGenerator {

    fun generate() :Lotto{
        val ballSet = BallGenerator.generateRandomBalls(6)
        return Lotto(ballSet)
    }
}