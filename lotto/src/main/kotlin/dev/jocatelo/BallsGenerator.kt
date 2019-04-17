package dev.jocatelo
import java.util.*
import kotlin.random.Random

class BallsGenerator {

    fun generateBalls() :Set<Int>{
        val calendar = Calendar.getInstance()
        val seed = calendar.timeInMillis
        return generateBalls(seed)
    }

    fun generateBalls(seed:Long): Set<Int>
    {
        var random = Random(seed)
        val balls = hashSetOf<Int>()
        while(balls.size <6){
            val number = random.nextInt(1, 45)
            balls.add(number)
        }
        return balls
    }
}