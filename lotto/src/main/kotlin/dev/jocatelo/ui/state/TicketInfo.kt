package dev.jocatelo.ui.state

import dev.jocatelo.Client

class TicketInfo (private val screen: Screen) : State{

    override fun output(): String {

        val lottoList = with(screen) { with(client) { ticketSet.joinToString() } }
        val background = """===== Ticket 정보 =====
            |구매한 Ticket 정보 :
            |%BALL_LIST%
            |===================
            |1. 돌아가기
            |명령>
            """.trimMargin("|")
        return background.replace("%BALL_LIST%", lottoList)
    }

    override fun change(number: String) {
        when (number) {
            "1" -> screen.state = Main(screen)
        }
    }
}