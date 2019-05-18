package dev.jocatelo.ui.state

import dev.jocatelo.Client

class AfterBuyTicket(private val screen: Screen, private val number: Int) : State {

    override fun output(): String {
        return "$number 장 구매가 완료되었습니다. 아무키나 눌러주세요."
    }

    override fun change(number: String) {
        screen.state = Main(screen)
    }

    override fun updateInfo(client: Client) {
        //Nothing
    }
}