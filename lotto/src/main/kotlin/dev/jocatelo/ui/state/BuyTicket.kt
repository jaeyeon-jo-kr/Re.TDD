package dev.jocatelo.ui.state

import dev.jocatelo.Client
import dev.jocatelo.TicketGenerator

class BuyTicket(private val screen: Screen, private var count:Int = 0) : State {

    override fun output(): String {
        return """========= 티켓 구매 ==========
            |몇 장 구매하시겠습니까?
        """.trimMargin("|")
    }

    override fun change(number: String) {
        count = number.toInt()
        updateInfo(screen.client)
        screen.state = AfterBuyTicket(screen, count)


    }

    fun updateInfo(client: Client) {
        repeat(count) { client.addLottoTicket(TicketGenerator.generateTicket(6)) }
    }
}