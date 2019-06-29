package dev.jocatelo

object LottoGenerator {
    fun generate(ballSet:Set<Ball>):Lotto {
        return Lotto(ballSet)
    }

    fun generate() :Lotto{
        val ballSet = BallGenerator.generateRandomBalls(6)
        return Lotto(ballSet)
    }
}