package dev.jocatelo

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class TestTicketGenerator {
    @Test
    fun `로또 티켓이 여러개의 당첨 번호 세트( lotto )를 가지고 있다`()
    {
        val lottoTicket = TicketGenerator.generate()
        MatcherAssert.assertThat(lottoTicket.size, Matchers.equalTo(6))
    }
}