package dev.jocatelo

object TicketGenerator {
    fun generateTicket(lottoCount: Int): LottoTicket {
        val lottoSet = hashSetOf<Lotto>()
        repeat(lottoCount) {
            lottoSet.add(LottoGenerator.generate())
        }
        return LottoTicket(lottoSet)
    }

}
