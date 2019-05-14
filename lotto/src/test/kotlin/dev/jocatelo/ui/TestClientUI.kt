package dev.jocatelo.ui

import dev.jocatelo.*
import junit.framework.TestCase.assertEquals
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.StringContains
import org.hamcrest.text.StringContainsInOrder
import org.junit.Test

//메뉴 목록은 다음과 같다.
//Ticket 정보 보기
//Ticket 만들기
//등수 확인
//종료하기
class TestClientUI
{
    @Test
    fun `사용자는 프로그램을 실행하면 메뉴를 확인한다`()
    {
        assertEquals(Status.MAIN_MENU.screen(), LottoMain.screen)
    }

    @Test
    fun `사용자는 첫 메뉴에서 종료하기를 입력하면 프로그램을 종료한다`()
    {
        LottoMain.processInput("4")
        assertEquals(false, LottoMain.running)
    }

    @Test
    fun `사용자는 첫 메뉴에서 로또 정보 보기를 클릭하면 구매한 로또 티켓을 보여준다`()
    {
        val ticket = TicketGenerator.generateTicket(1)
        val client: Client = LottoMain.client
        client.addLottoTicket(ticket)
        LottoMain.processInput("1")
        val lotto:Lotto = ticket.iterator().next()
        var expectedScreen : String = lotto.ballSet.toString()

        assertThat("Lotto Information", LottoMain.screen, StringContains(expectedScreen))

    }

}