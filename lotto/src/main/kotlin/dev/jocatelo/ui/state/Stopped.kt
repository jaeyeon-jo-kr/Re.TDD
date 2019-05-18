package dev.jocatelo.ui.state

import dev.jocatelo.Client

class Stopped (private val screen: Screen) : State{
    override fun updateInfo(client: Client) {
    }

    override fun output(): String {
        return "====== 종료되었습니다. =========="
    }

    override fun change(number: String) {
    }
}