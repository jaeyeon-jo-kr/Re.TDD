package dev.jocatelo

import org.jetbrains.annotations.Nullable
import kotlin.collections.forEach as forEach

class Client :PrizeChecker{
    @Nullable var ticket: LottoTicket? = null

    fun orderLottoTicket(count: Int) {
        ticket = TicketGenerator.generateTicket(count)
    }

    override fun expectedPrize(rankChecker: RankChecker): Int {
        val prizeInfo = PrizeInfo()
        var prize = 0

        val lottoSet = ticket?.iterator()

        lottoSet?.forEach { lotto ->
            val rank = rankChecker.askRank(lotto)
            prize += prizeInfo.getPrize(rank)
        }


        return prize
    }
}