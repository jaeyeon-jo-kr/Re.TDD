package dev.jocatelo

data class LottoTicket(private val `lotto's`:Set<Lotto>) {
    fun iterator(): Iterator<Lotto> = `lotto's`.iterator()

    val size: Int
        get() = `lotto's`.size

    fun sum(rankChecker:RankChecker, prizeInfo:PrizeInfo) {
        `lotto's`.sumBy { lotto ->
            var rank = rankChecker.askRank(lotto)
            var prize = prizeInfo.getPrize(rank)
            prize
        }
    }
}