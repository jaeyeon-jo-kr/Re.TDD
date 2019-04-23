

import dev.jocatelo.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*

import org.junit.Test
import kotlin.collections.ArrayList



@Suppress("NonAsciiCharacters")
class TestLotto {
    //랜덤넘버 6개를 생성한다
    @Test
    fun createSixNumber()
    {
        repeat(1000)
        {
            val generator = LottoGenerator()
            val ballSet = generator.generateLotto()

            assertThat(ballSet.size, equalTo(6))
        }
    }

    // 중복되지 않는 랜덤넘버를 생성한다
    @Test
    fun createRandomNumber()
    {

        val generator = LottoGenerator()
        val ballSet = generator.generateLotto()
        val testSet:ArrayList<Ball> = ArrayList()

        for(item in ballSet.iterator()){
            assertThat(testSet.toList(), not(hasItem(item)))
            testSet.add(item)
        }
    }

    //1에서 45까지의 넘버를 생성한다.
    @Test
    fun createNumberRange()
    {
        repeat(1000)
        {
            val generator = LottoGenerator()
            val ballSet: Lotto = generator.generateLotto()
            assertThat(
                ballSet.toList(),
                everyItem(allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(45)))
            )
        }
    }

    //당첨 번호 1세트는 보너스 번호를 가지고 있어야 한다.
    @Test
    fun winningNumbersHasBonusNumbers()
    {
        val generator = WinningLottoGenerator()
        val winningNumbers: WinningLotto = generator.generateLotto()

        assertThat(winningNumbers.hasBonusBall(), equalTo(true))
    }

    //당첨 번호들은 Bonus Ball을 가지고 있지 않아야 한다.
    @Test
    fun bonusNumberIsNotDuplicated()
    {
        repeat(1000)
        {
            val generator = WinningLottoGenerator()
            val winningNumbers: WinningLotto = generator.generateLotto()

            assertThat(winningNumbers, not(hasItem(winningNumbers.bonus)))
        }
    }

    //로또 티켓이 여러개의 당첨 번호 세트(Lotto)를 가지고 있다.
    @Test
    fun ticketReceiveLotto()
    {
        val generator = TicketGenerator()
        val lottoTicket = generator.generateTicket(3)

        assertThat(lottoTicket.lottoCount, equalTo(3))
    }

    //클라이언트는 로또 티켓을 주문한다.
    @Test
    fun clientCreateLottoTicket()
    {
        val client = Client()
        val count = 3
        client.orderLottoTicket(count)
        assertThat(client.ticket, notNullValue())
    }

    @Test
    fun `등수 확인자는 당첨 로또를 알고 있어야 한다`()
    {
        val generator = WinningLottoGenerator()
        val rankChecker:RankChecker = generator.generateLotto()

        assertThat(rankChecker, notNullValue())
    }

    @Test
    fun `등수 확인자는 로또를 제공받으면 등수를 제공한다`()
    {
        val generator = WinningLottoGenerator()
        val rankChecker:RankChecker = generator.generateLotto()
        repeat(1000) {
            val lotto = LottoGenerator().generateLotto()
            val rank = rankChecker.askRank(lotto)
            assertThat(rank, both(greaterThanOrEqualTo(0)).and(lessThanOrEqualTo(5)))
        }
    }
    @Test
    fun `당첨금 정보는 등수에 따른 보상금을 알려준다`()
    {
        val prizeInfo = PrizeInfo()
        val anyPrize = prizeInfo.getPrize(1)
        assertThat(anyPrize, instanceOf(Int::class.java))
    }

    @Test
    fun `클라이언트는 당첨금 확인자에게 로또 티켓을 보여주며 당첨금을 확인한다`()
    {
        val client = Client()
        client.orderLottoTicket(3)
        val prizeChecker = Client()
        val expectedPrize = prizeChecker.expectedPrize(client.ticket)

        assertThat(expectedPrize, instanceOf(Int::class.java))
    }



}