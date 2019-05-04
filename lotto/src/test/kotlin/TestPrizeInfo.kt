import dev.jocatelo.PrizeInfo
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class TestPrizeInfo {
    @Test
    fun `당첨금 정보는 등수에 따른 보상금을 알려준다`()
    {
        val prizeInfo = PrizeInfo()
        val anyPrize = prizeInfo.getPrize(1)
        MatcherAssert.assertThat(anyPrize, Matchers.instanceOf(Int::class.java))
    }

}