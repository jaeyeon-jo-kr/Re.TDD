package dev.jocatelo
import java.util.*
import kotlin.random.Random

class BallsGenerator {

    fun generateBalls() :BallSet{
        val calendar = Calendar.getInstance()
        val seed = calendar.timeInMillis
        return generateBalls(seed)
    }

    private fun generateBalls(seed:Long): BallSet
    {
        val random = Random(seed)
        val balls = BallHashSet()
        while(balls.size <6){
            val number = random.nextInt(1, 45)
            balls.add(number)
        }
        return balls
    }
}