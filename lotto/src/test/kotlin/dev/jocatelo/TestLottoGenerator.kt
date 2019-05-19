package dev.jocatelo

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class TestLottoGenerator
{
    @Test
    fun `넘버 6개를 자동으로 생성한다`()
    {
        val lotto = LottoGenerator.generate()
        MatcherAssert.assertThat(lotto.size, Matchers.equalTo(6))
    }

    @Test
    fun `넘버 6개를 수동으로 생성한다`()
    {
        val ballSet = setOf(1, 2, 3, 4, 5, 7 )
        val lotto = LottoGenerator.generate(ballSet)
        MatcherAssert.assertThat(lotto.size, Matchers.equalTo(6))
    }

    @Test
    fun `생성되는 값들은 중복되지 않아야 한다`()
    {

        val lotto = LottoGenerator.generate()
        val testSet = hashSetOf<Ball>()

        lotto.filter { true }.forEach { ball -> testSet.add(ball) }

        MatcherAssert.assertThat(lotto.size, Matchers.equalTo(testSet.size))
    }


    @Test
    fun `로도 생성기는 1에서 45까지의 넘버를 생성한다`()
    {
        val expected = (1 until 46).toHashSet()
        val loop = 100

        repeat(loop) {
            val lotto = LottoGenerator.generate()
            val ballSet = lotto.ballSet
            expected.removeAll(ballSet)
        }
        MatcherAssert.assertThat(expected.isEmpty(), Matchers.equalTo(true))
    }
}