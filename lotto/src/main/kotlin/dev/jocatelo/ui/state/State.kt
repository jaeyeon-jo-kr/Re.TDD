package dev.jocatelo.ui.state

import dev.jocatelo.Client

interface State {
    fun output():String
    fun change(number:String)
    fun updateInfo(client: Client)
}