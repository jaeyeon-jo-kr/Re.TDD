package dev.jocatelo

import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.core.IsNot
import org.hamcrest.core.IsNull
import org.junit.Test

class TestTicketGenerator {
    @Test
    fun `로또 티켓이 여러개의 당첨 번호 세트( lotto )를 가지고 있다`()
    {
        val lottoTicket = TicketGenerator.generate()
        assertThat(lottoTicket.size, Matchers.equalTo(6))
    }

    @Test
    fun `로또 티켓은 구매한 날짜를 가지고 있다`()
    {
        val lottoTicket = TicketGenerator.generate()

        assertThat("구매 날짜", lottoTicket.getPurchaseDate(), IsNot(IsNull()))
    }

}