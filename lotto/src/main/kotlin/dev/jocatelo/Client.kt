package dev.jocatelo

class Client :PrizeChecker{
    var ticket : LottoTicket = LottoTicket()

    fun orderLottoTicket(count: Int) {
        val generator = TicketGenerator()
        ticket = generator.generateTicket(count)
    }
    override fun expectedPrize(rankChecker: RankChecker): Int {
        val prizeInfo = PrizeInfo()
        var prize = 0

        ticket.forEach{
            lotto -> val rank = rankChecker.askRank(lotto)
            prize += prizeInfo.getPrize(rank)
        }
        return prize
    }
}