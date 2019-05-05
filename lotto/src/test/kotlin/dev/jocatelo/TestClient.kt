package dev.jocatelo

import dev.jocatelo.Client
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class TestClient {
    @Test
    fun `클라이언트는 로또 티켓을 주문한다`()
    {
        val client = Client()
        client.addLottoTicket(TicketGenerator.generateTicket(3))

    }
}