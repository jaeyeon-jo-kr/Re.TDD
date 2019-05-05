package dev.jocatelo

object LottoGenerator {

    fun generate() :Lotto{
        val ballSet = hashSetOf<Ball>()
        while(ballSet.size < 6){
            val ball = BallGenerator.generateRandom()
            ballSet.add(ball)
        }
        return Lotto(ballSet)
    }
}