

import dev.jocatelo.BallSet
import dev.jocatelo.BallsGenerator
import dev.jocatelo.WinningBallSet
import dev.jocatelo.WinningBallsGenerator
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.hamcrest.Matchers.lessThanOrEqualTo

import org.junit.Test
import kotlin.collections.ArrayList

class TestLotto {
    //랜덤넘버 6개를 생성한다
    @Test
    fun createSixNumber()
    {
        repeat(1000)
        {
            val generator = BallsGenerator()
            val ballSet = generator.generateBalls()

            assertThat(ballSet.size, equalTo(6))
        }
    }

    // 중복되지 않는 랜덤넘버를 생성한다
    @Test
    fun createRandomNumber()
    {

        val generator = BallsGenerator()
        val ballSet = generator.generateBalls()
        val testSet:ArrayList<Int> = ArrayList()

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
            val generator = BallsGenerator()
            val ballSet: Set<Int> = generator.generateBalls()
            assertThat(
                ballSet.toList(),
                everyItem(allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(45)))
            )
        }
    }

    //당첨 번호 1세트는 보너스 번호를 가지고 있어야 한다.
    @Test
    fun bonusNumbers()
    {
        val generator = WinningBallsGenerator()
        val winningNumbers: WinningBallSet = generator.generateBalls()

        assertThat(winningNumbers.hasBonusBall(), equalTo(true))
    }
}