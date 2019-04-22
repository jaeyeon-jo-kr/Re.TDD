package dev.jocatelo
import java.util.*
import kotlin.random.Random

class LottoGenerator {

    fun generateBalls() :Lotto{
        val calendar = Calendar.getInstance()
        val seed = calendar.timeInMillis
        return generateBalls(seed)
    }

    private fun generateBalls(seed:Long): Lotto
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