package dev.jocatelo

object WinningLottoGenerator {

    fun generateLotto(): WinningLotto {
        val basic = LottoGenerator.generate()
        var bonus: Int
        do{
            bonus = BallGenerator.generateRandom()
        }while(basic.contains(bonus))
        return WinningLotto(basic, bonus)
    }
}