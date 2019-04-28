

import dev.jocatelo.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*

import org.junit.Test
import kotlin.collections.ArrayList



@Suppress("NonAsciiCharacters")
class TestLotto {

    @Test
    fun `넘버 6개를 생성한다`()
    {
        repeat(1000)
        {
            val ballSet = LottoGenerator.generateLotto()
            assertThat(ballSet.size, equalTo(6))
        }
    }

    @Test
    fun `중복되지 않는 넘버를 생성한다`()
    {

        val ballSet = LottoGenerator.generateLotto()
        val testSet:ArrayList<Ball> = ArrayList()

        for(item in ballSet.iterator()){
            assertThat(testSet.toList(), not(hasItem(item)))
            testSet.add(item)
        }
    }


    @Test
    fun `1에서 45까지의 넘버를 생성한다`()
    {
        val expected = (1 until 46).toHashSet()
        var loopCount = 100
        while( loopCount > 0 && expected.isNotEmpty() )
        {
            val lotto: Lotto = LottoGenerator.generateLotto()
            lotto.iterator().forEach { ball -> expected.remove(ball) }
            loopCount--
        }
        assertThat(expected.isEmpty(), equalTo(true))
    }


    //.
    @Test
    fun `당첨 Ball들은 Bonus Ball을 가지고 있지 않아야 한다`()
    {
        repeat(1000)
        {
            val winningNumbers: WinningLotto = WinningLottoGenerator.generateLotto()

            assertThat(winningNumbers, not(hasItem(winningNumbers.bonus)))
        }
    }


    @Test
    fun `로또 티켓이 여러개의 당첨 번호 세트(Lotto)를 가지고 있다`()
    {
        val lottoTicket = TicketGenerator.generateTicket(3)
        assertThat(lottoTicket.size, equalTo(3))
    }

    @Test
    fun `클라이언트는 로또 티켓을 주문한다`()
    {
        val client = Client()
        val count = 3
        client.orderLottoTicket(count)
        assertThat(client.ticket, notNullValue())
    }

    @Test
    fun `등수 확인자는 당첨 로또를 알고 있어야 한다`()
    {
        val rankChecker:RankChecker = WinningLottoGenerator.generateLotto()
        assertThat(rankChecker, notNullValue())
    }

    @Test
    fun `등수 확인자는 로또를 제공받으면 등수를 제공한다`()
    {

        val rankChecker:RankChecker = WinningLottoGenerator.generateLotto()
        repeat(1000) {
            val lotto = LottoGenerator.generateLotto()
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
        val winningLotto = WinningLottoGenerator.generateLotto()
        val expectedPrize = client.expectedPrize(winningLotto)

        assertThat(expectedPrize, instanceOf(Int::class.java))
    }



}