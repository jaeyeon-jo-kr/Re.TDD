package dev.jocatelo

class TicketGenerator {
    fun generateTicket(lottoCount: Int): LottoTicket {
        val ticket = LottoTicket()
        ticket.`generateLotto's`(lottoCount)
        return ticket
    }

}
