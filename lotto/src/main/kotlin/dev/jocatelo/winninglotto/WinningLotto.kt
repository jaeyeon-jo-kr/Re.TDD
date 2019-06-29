package dev.jocatelo.winninglotto

import dev.jocatelo.Ball
import dev.jocatelo.Lotto
import java.util.*


data class WinningLotto(private var lotto: Lotto, val bonus: Ball) :  Iterable<Ball> {
    override fun iterator(): Iterator<Ball> =  lotto.iterator()
}