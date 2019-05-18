package dev.jocatelo.ui

import dev.jocatelo.Client
import dev.jocatelo.ui.state.Main
import dev.jocatelo.ui.state.Screen
import dev.jocatelo.ui.state.State

object LottoMain {
    val screen = Screen()


    init{}

    fun processInput(input:String)
    {
        screen.processInput(input)
    }
    fun isRunning(): Boolean = !screen.isStopped()
    fun reset() {
        screen.reset()
    }
}
