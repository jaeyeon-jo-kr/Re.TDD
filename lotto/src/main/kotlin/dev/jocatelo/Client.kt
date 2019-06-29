package dev.jocatelo

import dev.jocatelo.winninglotto.WinningLotto
import kotlin.collections.forEach as forEach

const val TICKET_PRICE = 5000

class Client{

    var money: Int = 0
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

    fun checkMoney()
    {
        if(money < TICKET_PRICE)
            throw NotEnoughMoneyException()
    }
    fun orderRandomTickets(count: Int) {
        repeat(count) {
            checkMoney()
            money -= TICKET_PRICE
            addLottoTicket(TicketGenerator.generate())
        }
    }

    fun orderManualTicket(lottoSet: Set<Lotto>) {
        checkMoney()
        money -= TICKET_PRICE
        addLottoTicket(TicketGenerator.generate(lottoSet))
    }

    fun tryGetPrize()
    {
//        val prizeChecker = PrizeChecker()
//
//        prizeChecker.getPrizeByLotto()
    }
}