package dev.jocatelo

import kotlin.random.Random

class WinningLottoGenerator {
    private var lottoGenerator:LottoGenerator = LottoGenerator()
    fun generateLotto(): WinningLotto {
        val basic = lottoGenerator.generateLotto()
        var bonus: Int
        do{
            bonus = Random.nextInt(1,45)
        }while(basic.contains(bonus))
        return WinningLotto(basic, bonus)
    }
}