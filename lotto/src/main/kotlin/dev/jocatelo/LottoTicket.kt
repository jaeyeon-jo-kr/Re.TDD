package dev.jocatelo

data class LottoTicket(private val `lotto's`:Set<Lotto>) {
    fun iterator(): Iterator<Lotto> = `lotto's`.iterator()

    val size: Int
        get() = `lotto's`.size
}