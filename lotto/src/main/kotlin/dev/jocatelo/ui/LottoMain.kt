package dev.jocatelo.ui

import dev.jocatelo.Client

object LottoMain {
    var running: Boolean = true
    var status: Status = Status.MAIN_MENU
    var screen: String = status.screen()
    val client:Client = Client()
    init{
        running = true
    }



    fun processInput(input:String)
    {
        if(status == Status.MAIN_MENU){
            when (input) {
                "4"-> running = false
                "1"-> {
                    status = Status.TICKET_INFO
                    screen = status.screen()
                }
            }
        }
    }




}
