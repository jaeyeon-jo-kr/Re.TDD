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

    fun ticketCount():Int
    {
        return ticketSet.size
    }

    fun orderRandomTickets(count: Int) {
        repeat(count) {
            addLottoTicket(TicketGenerator.generate())
        }
    }

    fun orderManualTicket(lottoSet: Set<Lotto>) {
        addLottoTicket(TicketGenerator.generate(lottoSet))
    }
}