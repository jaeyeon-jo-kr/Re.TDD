package dev.jocatelo

import kotlin.random.Random

class WinningBallsGenerator {
    var ballsGenerator:BallsGenerator = BallsGenerator()
    fun generateBalls(): WinningBalls {
        val basic = ballsGenerator.generateBalls()
        var bonus = Random.nextInt(1,45)

        while(basic.contains(bonus)){
            bonus = Random.nextInt(1,45)
        }
        return WinningBalls(basic, bonus)
    }
}