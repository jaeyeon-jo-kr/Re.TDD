package dev.jocatelo

import dev.jocatelo.date.AnnounceDate
import dev.jocatelo.winninglotto.WinningInfo
import dev.jocatelo.winninglotto.WinningLotto
import dev.jocatelo.winninglotto.WinningLottoGenerator
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.equalTo
import org.hamcrest.core.Is
import org.hamcrest.core.IsNot
import org.hamcrest.core.IsNull
import org.junit.Test
import java.time.LocalDate

class TestWinningLottoGenerator {
    @Test
    fun `당첨 Ball들은 Bonus Ball을 가지고 있지 않아야 한다`()
    {
        val winningNumbers: WinningLotto = WinningLottoGenerator.generateLotto()
        assertThat(winningNumbers, Matchers.not(Matchers.hasItem(winningNumbers.bonus)))
    }

    @Test
    fun `등수 확인자는 당첨 로또를 알고 있어야 한다`()
    {
        val winningLotto: WinningLotto = WinningLottoGenerator.generateLotto()
        assertThat(winningLotto, Matchers.notNullValue())
    }

    @Test
    fun `등수 확인자는 로또를 제공받으면 0에서 5까지의 등수를 제공한다`()
    {
        val winningLotto: WinningLotto = WinningLottoGenerator.generateLotto()
        val lotto = LottoGenerator.generate()

        val rank = PrizeChecker(winningLotto).getRank(lotto)
        assertThat(
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

        assertThat(expectedPrize, Matchers.instanceOf(Int::class.java))
    }

    @Test
    fun `당첨 로또는 회차 정보를 가지고 있어야 한다`()
    {
        val winningLotto: WinningLotto = WinningLottoGenerator.generateLotto()
        val winningInfo = WinningInfo(winningLotto)
        winningInfo.round = 5

        assertThat(winningInfo.round, Is(Matchers.equalTo(5)))
    }

    @Test
    fun `당첨 로또는 발표 정보를 가지고 있어야 한다`()
    {
        val winningLotto =  WinningLottoGenerator.generateLotto()
        val winningInfo = WinningInfo(winningLotto)
        winningInfo.announceDate = AnnounceDate(LocalDate.of(2019, 5, 25))

        val expectedDate = AnnounceDate("2019-05-25")

        assertThat(winningInfo.announceDate, equalTo(expectedDate))

    }
}