package dev.jocatelo

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot
import org.junit.Test
import org.junit.rules.ExpectedException

class TestClient {
    @Test
    fun `클라이언트는 자동 로또 티켓을 3장 주문하면 주문 갯수가 3이 된다`()
    {
        val client = Client()
        client.money = 50_000
        client.orderRandomTickets(3)
        assertThat("클라이언트 주문 갯수", client.ticketCount(), IsEqual(3))
    }

    @Test
    fun `클라이언트는 로또를 6개 만들고, 로또 티켓을 주문하면 티켓이 1장 추가된다`()
    {
        val client = Client()
        client.money = 50_000
        val lottoSet = setOf(
            LottoGenerator.generate(setOf(1,2,3,4,5,6)),
            LottoGenerator.generate(setOf(1,2,3,4,5,7)),
            LottoGenerator.generate(setOf(1,2,3,4,5,8)),
            LottoGenerator.generate(setOf(1,2,3,4,5,9)),
            LottoGenerator.generate(setOf(1,2,3,4,5,10)),
            LottoGenerator.generate(setOf(1,2,3,4,5,11))
        )
        client.orderManualTicket(lottoSet)
        assertThat("클라이언트 수동 주문", client.ticketCount(), IsEqual(1))
    }

    @Test
    fun `클라이언트는 티켓을 주문할 때 5000원을 내므로 랜덤 티켓 3장을 주문하면 돈이 15000원 줄어든다`()
    {
        val client = Client()
        client.money = 60_000
        client.orderRandomTickets(3)

        assertThat("티켓 주문", client.money, IsEqual(45_000))
    }

    @Test(expected = NotEnoughMoneyException::class)
    fun `돈이 부족하면 로또를 살 수 없다`()
    {
        val client = Client()
        client.money = 1_000
        client.orderRandomTickets(1)
    }


}