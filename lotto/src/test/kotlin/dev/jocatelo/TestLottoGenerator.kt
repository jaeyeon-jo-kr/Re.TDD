package dev.jocatelo

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class TestLottoGenerator
{
    @Test
    fun `넘버 6개를 생성한다`()
    {
        val ballSet = LottoGenerator.generate()
        MatcherAssert.assertThat(ballSet.size, Matchers.equalTo(6))
    }

    @Test
    fun `중복되지 않는 넘버를 생성한다`()
    {

        val ballSet = LottoGenerator.generate()
        val testSet = hashSetOf<Ball>()

        ballSet.filter { true }.forEach { ball -> testSet.add(ball) }

        MatcherAssert.assertThat(ballSet.size, Matchers.equalTo(testSet.size))
    }


    @Test
    fun `1에서 45까지의 넘버를 생성한다`()
    {
        val expected = (1 until 46).toHashSet()
        val loop = 100

        (0 until loop).forEach{
            expected.removeAll(LottoGenerator.generate().ballSet)
        }
        MatcherAssert.assertThat(expected.isEmpty(), Matchers.equalTo(true))
    }
}