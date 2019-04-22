package dev.jocatelo

class Client {
    var ticket : LottoTicket? = null

    fun orderLottoTicket(count: Int) {
        ticket = LottoTicket()
    }
}