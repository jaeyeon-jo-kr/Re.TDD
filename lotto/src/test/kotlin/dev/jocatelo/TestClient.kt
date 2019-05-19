package dev.jocatelo

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Test

class TestClient {
    @Test
    fun `클라이언트는 자동 로또 티켓을 3장 주문하면 주문 갯수가 3이 된다`()
    {
        val client = Client()
        client.orderRandomTickets(3)
        assertThat("클라이언트 주문 갯수", client.ticketCount(), IsEqual(3))
    }

    @Test
    fun `클라이언트는 로또를 6개 만들고, 로또 티켓을 주문하면 티켓이 1장 추가된다`()
    {
        val client = Client()

        val lottoSet = setOf(
            LottoGenerator.generate(setOf(1,2,3,4,5,6)),
            LottoGenerator.generate(setOf(1,2,3,4,5,7)),
            LottoGenerator.generate(setOf(1,2,3,4,5,8)),
            LottoGenerator.generate(setOf(1,2,3,4,5,9)),
            LottoGenerator.generate(setOf(1,2,3,4,5,10)),
            LottoGenerator.generate(setOf(1,2,3,4,5,11))
        )
        client.orderManualTicket(lottoSet)
        assertThat("클라이언트 주문 갯수", client.ticketCount(), IsEqual(1))
    }
}