import dev.jocatelo.BallsGenerator
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class TestLotto {
    @Test
    fun testGenerateRandomNumber()
    {
        val generator = BallsGenerator()
        val ballSet = generator.generateBalls()

        assertThat(ballSet.number, equalTo(6))

    }
}