package dev.jocatelo

object TicketGenerator {
    fun generateTicket(lottoCount: Int): LottoTicket {
        val lottoSet = hashSetOf<Lotto>()
        repeat(lottoCount) {
            lottoSet.add(LottoGenerator.generateLotto())
        }
        return LottoTicket(lottoSet)
    }

}
