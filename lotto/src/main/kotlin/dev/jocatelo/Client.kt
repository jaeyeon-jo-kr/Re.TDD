package dev.jocatelo

import kotlin.collections.forEach as forEach

class Client{
    val ticketSet: HashSet<LottoTicket> = hashSetOf()
        get() = field

    fun addLottoTicket(ticket:LottoTicket) {
        ticketSet.add(ticket)
    }

    fun expectedPrize(winningLotto: WinningLotto): Int {
        return ticketSet.sumBy{
            it.sum(winningLotto)
        }
    }

    fun clear()
    {
        ticketSet.clear()

    }




}