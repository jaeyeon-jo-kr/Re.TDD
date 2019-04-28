package dev.jocatelo

object LottoGenerator {

    fun generateLotto() :Lotto{
        val ballSet = hashSetOf<Ball>()
        while(ballSet.size < 6){
            val number = BallGenerator.generateRandom()
            ballSet.add(number)
        }
        return Lotto(ballSet)
    }
}