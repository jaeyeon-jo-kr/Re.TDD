package dev.jocatelo.ui.state

import dev.jocatelo.Client

class Screen
{
    val client = Client()
    var state:State = Main(this)
    fun processInput(input: String) {
        state.change(input)
    }

    fun output() : String =  state.output()

    fun isStopped() : Boolean {
        return state is Stopped
    }

    fun reset() {
        state = Main(this)
    }


}