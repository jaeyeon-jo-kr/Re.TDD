package dev.jocatelo

import org.jetbrains.annotations.Nullable
import kotlin.collections.forEach as forEach

class Client{
    val ticketSet: HashSet<LottoTicket> = hashSetOf()
    fun addLottoTicket(ticket:LottoTicket) {
        ticketSet.add(ticket)
    }

    fun expectedPrize(winningLotto: WinningLotto): Int {
        val prizeInfo = PrizeInfo()
        var prize = 0

        ticketSet.forEach{
            it.sum(winningLotto, prizeInfo)
        }

        return prize
    }


}