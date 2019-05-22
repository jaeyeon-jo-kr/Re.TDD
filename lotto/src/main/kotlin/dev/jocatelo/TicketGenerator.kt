package dev.jocatelo

import java.util.*

object TicketGenerator {
    const val lotttoCount = 6

    fun generate(): LottoTicket {
        val lottoSet = hashSetOf<Lotto>()
        repeat(lotttoCount) {
            lottoSet.add(LottoGenerator.generate())
        }
        return LottoTicket(lottoSet, purchaseDate = Calendar.getInstance())
    }

    fun generate(lottoSet:Set<Lotto>) : LottoTicket
    {
        if(lottoSet.size != 6)
            throw InvalidSizeException()
        return LottoTicket(lottoSet, Calendar.getInstance())
    }

}
