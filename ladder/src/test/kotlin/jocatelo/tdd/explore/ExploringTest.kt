package jocatelo.tdd.explore

import jocatelo.tdd.ladder.LadderCreator
import org.junit.Test
import kotlin.test.assertNotNull

class ExploringTest {

    @Test
    fun `It can be Exploring ladder`()
    {
        // given
        val col = 0
        val explorer = Explorer(col)
        val creator = LadderCreator(7,7);
        val ladder = creator.create()

        // when
        explorer.explore(ladder)

        // then
        assertNotNull(explorer.result)
    }
}