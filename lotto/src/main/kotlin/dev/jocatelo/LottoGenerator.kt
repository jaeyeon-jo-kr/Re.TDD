package dev.jocatelo
import java.util.*
import kotlin.random.Random

class LottoGenerator {

    fun generateLotto() :Lotto{
        val calendar = Calendar.getInstance()
        val seed = calendar.timeInMillis
        return generateLotto(seed)
    }

    private fun generateLotto(seed:Long): Lotto
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