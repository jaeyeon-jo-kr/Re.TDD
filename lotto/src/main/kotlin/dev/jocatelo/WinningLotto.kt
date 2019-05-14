package dev.jocatelo


data class WinningLotto(private var lotto:Lotto, val bonus: Ball) :  Iterable<Ball> {
    override fun iterator(): Iterator<Ball> =  lotto.iterator()
}