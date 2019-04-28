package dev.jocatelo


class WinningLotto(private var lotto:Lotto, val bonus: Ball) : RankChecker, Iterable<Ball> {

    override fun iterator(): Iterator<Ball> =  lotto.iterator()

    private fun askRankBonus(client:Lotto):Int =
        when (client.contains(bonus)) {
            true -> 2
            false -> 3
        }

    override fun askRank(client: Lotto): Int =
        when (client.filter { number -> lotto.contains(number) }.count()) {
            6 -> 1
            5 -> askRankBonus(client)
            4 -> 4
            3 -> 5
            else -> 0
        }

}