package dev.jocatelo

import kotlin.random.Random

class WinningBallsGenerator {
    var lottoGenerator:LottoGenerator = LottoGenerator()
    fun generateBalls(): WinningBalls {
        val basic = lottoGenerator.generateBalls()
        var bonus = Random.nextInt(1,45)

        while(basic.contains(bonus)){
            bonus = Random.nextInt(1,45)
        }
        return WinningBalls(basic, bonus)
    }
}