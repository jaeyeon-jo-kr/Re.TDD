package dev.jocatelo

import kotlin.random.Random

object BallGenerator {
    fun generateRandom():Ball= generate(Random.nextInt(1,46))
    fun generateRandomBalls(count:Int):Set<Ball>{
        val ballSet = hashSetOf<Ball>()
        while(ballSet.size < count)
        {
            ballSet.add(generateRandom())
        }
        return ballSet
    }
    fun generate(number:Int):Ball = number
}
typealias Ball = Int