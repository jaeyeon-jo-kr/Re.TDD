package dev.jocatelo.ui

import dev.jocatelo.*
import dev.jocatelo.ui.state.*
import junit.framework.TestCase.assertEquals
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot
import org.hamcrest.core.StringContains
import org.junit.Before
import org.junit.Test

//메뉴 목록은 다음과 같다.
//Ticket 정보 보기
//Ticket 만들기
//등수 확인
//종료하기
class TestClientUI
{
    @Before
    fun initialize()
    {
        LottoMain.reset()
    }
    @Test
    fun `사용자는 프로그램을 실행하면 메뉴를 확인한다`()
    {
        var mockScreen = Screen()
        var expected = Main(mockScreen)
        assertEquals(expected.output(), LottoMain.screen.output())
    }

    @Test
    fun `사용자는 첫 메뉴에서 종료하기를 입력하면 프로그램을 종료한다`()
    {
        LottoMain.processInput("4")
        assertEquals(false, LottoMain.isRunning())
    }

    @Test
    fun `사용자는 첫 메뉴에서 로또 정보 보기를 선택하면 구매한 로또 티켓을 보여준다`()
    {
        val ticket = TicketGenerator.generate()
        val client: Client = LottoMain.screen.client
        client.addLottoTicket(ticket)
        LottoMain.processInput("1")

        val lotto:Lotto = ticket.iterator().next()
        var expectedScreen : String = lotto.ballSet.toString()

        assertThat("Lotto Information", LottoMain.screen.output(), StringContains(expectedScreen))
    }

    @Test
    fun `로또 정보를 보는 것을 종료하면 메인 메뉴로 돌아갈 수 있다`()
    {
        LottoMain.processInput("1")
        LottoMain.processInput("1")

        var mockScreen = Screen()
        var expected = Main(mockScreen)
        assertThat("Return to main menu", LottoMain.screen.output(), IsEqual(expected.output()))
    }

    @Test
    fun `티켓 사기를 수행하면 클라이언트가 티켓을 사는 메뉴를 보여준다`()
    {
        LottoMain.processInput("2")

        var mockScreen = Screen()
        var expected = BuyTicket(mockScreen)

        assertThat("Buy Ticket", LottoMain.screen.output(), IsEqual(expected.output()))
    }

    @Test
    fun `티켓을 샀으면 티켓을 산 결과를 보여준다`()
    {
        LottoMain.processInput("2")
        LottoMain.processInput("3")

        var mockScreen = Screen()
        var expected = AfterBuyTicket(mockScreen, 3)

        assertThat("After Buy Ticket", LottoMain.screen.output(), IsEqual(expected.output()))
    }

    @Test
    fun `티켓을 샀으면 티켓을 산 횟수를 보여준다`()
    {
        LottoMain.processInput("2")
        LottoMain.processInput("3")

        assertThat("Show Ticket Count", LottoMain.screen.output(), StringContains("3"))
    }

    @Test
    fun `티켓을 샀으면 로또 정보를 볼 때 티켓 정보가 비어있으면 안된다`()
    {
        LottoMain.processInput("2")// 티켓 구매
        LottoMain.processInput("3")// 티켓 갯수
        LottoMain.processInput("")// 확인
        LottoMain.processInput("1")//티켓 확인

        var mockScreen = Screen()
        var notExpected = TicketInfo(mockScreen).output()

        assertThat("Show Ticket Count", LottoMain.screen.output(), IsNot(IsEqual(notExpected)))
    }

}