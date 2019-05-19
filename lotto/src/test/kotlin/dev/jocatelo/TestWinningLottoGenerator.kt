package dev.jocatelo

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class TestWinningLottoGenerator {
    @Test
    fun `당첨 Ball들은 Bonus Ball을 가지고 있지 않아야 한다`()
    {
        val winningNumbers: WinningLotto = WinningLottoGenerator.generateLotto()
        MatcherAssert.assertThat(winningNumbers, Matchers.not(Matchers.hasItem(winningNumbers.bonus)))
    }

    @Test
    fun `등수 확인자는 당첨 로또를 알고 있어야 한다`()
    {
        val winningLotto: WinningLotto = WinningLottoGenerator.generateLotto()
        MatcherAssert.assertThat(winningLotto, Matchers.notNullValue())
    }

    @Test
    fun `등수 확인자는 로또를 제공받으면 0에서 5까지의 등수를 제공한다`()
    {

        val winningLotto:WinningLotto = WinningLottoGenerator.generateLotto()
        val lotto = LottoGenerator.generate()

        val rank = PrizeChecker(winningLotto).getRank(lotto)
        MatcherAssert.assertThat(
            rank,
            Matchers.both(Matchers.greaterThanOrEqualTo(0)).and(Matchers.lessThanOrEqualTo(5))
        )
    }

    @Test
    fun `클라이언트는 당첨금 확인자에게 로또 티켓을 보여주며 당첨금을 확인한다`()
    {
        val client = Client()
        client.addLottoTicket(TicketGenerator.generate())
        val winningLotto = WinningLottoGenerator.generateLotto()
        val expectedPrize = client.expectedPrize(winningLotto)

        MatcherAssert.assertThat(expectedPrize, Matchers.instanceOf(Int::class.java))
    }
}