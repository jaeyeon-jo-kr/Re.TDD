package dev.jocatelo
import kotlin.random.Random

class BallsGenerator {

    fun generateBalls(): BallSet
    {
        val balls = hashSetOf<Int>()
        repeat(6) {balls.add(Random.nextInt(1, 45))}
        return BallHashSet(balls)
    }
}