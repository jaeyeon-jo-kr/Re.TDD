package dev.jocatelo

import kotlin.random.Random

object WinningLottoGenerator {

    fun generateLotto(): WinningLotto {
        val basic = LottoGenerator.generateLotto()
        var bonus: Int
        do{
            bonus = BallGenerator.generateRandom()
        }while(basic.contains(bonus))
        return WinningLotto(basic, bonus)
    }
}