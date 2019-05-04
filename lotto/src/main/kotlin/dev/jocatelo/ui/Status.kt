package dev.jocatelo.ui

enum class Status {
    MAIN_MENU
    {
        override val screen = UIString.MAIN_MENU.toString()
    };

    abstract val screen:String
}