package dev.jocatelo.ui

import java.util.*

fun main() {

    while(LottoMain.isRunning()) {
        print(LottoMain.screen.output())
        val input = readLine()!!
        LottoMain.processInput(input)
    }
}