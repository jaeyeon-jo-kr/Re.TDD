package dev.jocatelo.ui.state

import dev.jocatelo.Client

class Screen
{
    var state:State = Main(this)
    fun processInput(input: String) {
        state.change(input)
    }

    fun update(client: Client) {
        state.updateInfo(client)
    }

    fun output() : String =  state.output()

    fun isStopped() : Boolean {
        return state is Stopped
    }

    fun reset() {
        state = Main(this)
    }


}