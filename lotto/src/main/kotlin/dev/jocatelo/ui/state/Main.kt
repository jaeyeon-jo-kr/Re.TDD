package dev.jocatelo.ui.state

import dev.jocatelo.Client

class Main(private val screen: Screen) : State {

    override fun output(): String =
        """==== Lotto ====
                |1. Ticket 정보 보기
                |2. Ticket 사기
                |3. 등수 확인
                |4. 종료하기
                |명령>
                """.trimMargin("|")

    override fun change(number: String) {
        when (number) {
            "1" -> screen.state = TicketInfo(screen)
            "2" -> screen.state = BuyTicket(screen)
            "4" -> screen.state = Stopped(screen)

        }
    }

}