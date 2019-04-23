package dev.jocatelo

interface PrizeChecker {
    fun expectedPrize(ticket: LottoTicket): Int
}