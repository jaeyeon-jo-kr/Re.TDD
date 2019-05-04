package dev.jocatelo.ui

object LottoMain {
    var running: Boolean = true
    var status:Status = Status.MAIN_MENU
    var screen: String = status.screen
    init{
        running = true
    }

    fun processInput(input:String)
    {
        if(status == Status.MAIN_MENU && input.equals("4")){
            running = false
        }
    }



}
