package dev.jocatelo

class Client {
    var ticket : LottoTicket? = null

    fun orderLottoTicket(count: Int) {
        val generator = TicketGenerator()
        ticket = generator.generateTicket(count)
    }
}