package dev.jocatelo

import dev.jocatelo.winninglotto.WinningLotto
import java.util.*

data class LottoTicket(private val `lotto's`:Set<Lotto>, private val purchaseDate:Calendar) {

    fun iterator(): Iterator<Lotto> = `lotto's`.iterator()

    val size: Int
        get() = `lotto's`.size

    fun sum(winningLotto: WinningLotto) :Int {
        return `lotto's`.sumBy { lotto ->
            PrizeChecker(winningLotto).getPrizeByLotto(lotto)
        }
    }

    fun getPurchaseDate(): Calendar {
        return purchaseDate
    }
}