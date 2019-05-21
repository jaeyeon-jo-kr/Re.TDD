package dev.jocatelo

class PrizeChecker(private val winningLotto: WinningLotto) {

    private fun askRankBonus(client:Lotto):Int=
        when (client.contains(winningLotto.bonus)) {
            true -> 2
            false -> 3
        }
    fun getRank(lotto: Lotto)=
        when (lotto.filter { number -> lotto.contains(number) }.count()) {
            6 -> 1
            5 -> askRankBonus(lotto)
            4 -> 4
            3 -> 5
            else -> 0
        }

    fun getPrizeByLotto(lotto: Lotto):Int{
        return getExpectedPrize(getRank(lotto))
    }

    fun getExpectedPrize(rank: Int): Int {
        return when (rank) {
            1 -> 1_000_000
            2 -> 500_000
            3 -> 300_000
            4 -> 50_000
            5 -> 5_000
            else -> 0
        }
    }

}
