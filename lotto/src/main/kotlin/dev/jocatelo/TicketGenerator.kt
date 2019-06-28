package dev.jocatelo

import java.util.*

object TicketGenerator {
    private const val lottoCount:Int = 6

    fun generate(): LottoTicket {
        val lottoSet = hashSetOf<Lotto>()
        repeat(lottoCount) {
            lottoSet.add(LottoGenerator.generate())
        }
        return LottoTicket(lottoSet, purchaseDate = Calendar.getInstance())
    }

    fun generate(lottoSet:Set<Lotto>) : LottoTicket
    {
        if(lottoSet.size != lottoCount)
            throw InvalidSizeException()
        return LottoTicket(lottoSet, Calendar.getInstance())
    }

}
