

import dev.jocatelo.BallsGenerator
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.hamcrest.Matchers.lessThanOrEqualTo

import org.junit.Test
import java.util.*
import kotlin.random.Random

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

    // 랜덤넘버를 생성한다
    @Test
    fun createRandomNumber()
    {
        val seed = 1000L
        val generator = BallsGenerator()
        val ballSet:Set<Int> = generator.generateBalls(seed)

        val testSet = hashSetOf<Int>()
        val random = Random(seed)
        repeat(6 ){ testSet.add(random.nextInt(1, 45)) }

        assertThat(ballSet.toList(), equalTo(testSet.toList()))
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
}