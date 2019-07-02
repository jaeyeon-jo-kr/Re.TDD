import org.junit.Assert.assertThat
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CreateLadderTest {
    @Test
    fun `사다리 제작자에게 사다리를 만들어달라고 요청하면 사다리를 만들어준다`()
    {
        //given
        val ladderCreator = LadderCreator()

        //when
        val ladder = ladderCreator.create()

        //then
        assertNotNull(ladder)
    }

    @Test
    fun `사다리 맵의 정보를 사다리 파트 제작자에게 주면 사다리 파트를 만들어준다`()
    {
        //given
        val ladderPartCreator = LadderPartCreator()
        val ladderCreator = LadderCreator()
        val ladderPartInfo = ladderCreator.createPartInfo()

        //when
        val part = ladderPartCreator.createPart(ladderPartInfo)

        //then
        assertNotNull(part)
    }

    //
    @Test
    fun `사다리 파트 중에는 내려가는 문자가 있다`()
    {
        //given


        //when
        val ladderPart = LadderPartFactory.createDown()

        //then
        assertEquals('│', ladderPart.partChar)
    }

    @Test
    fun `사다리 파트 중에는├ 있다`()
    {
        //given

        //when
        val ladderPart = LadderPartFactory.createTurnRight()

        //then
        assertEquals('├', ladderPart.partChar)
    }

    @Test
    fun `사다리 파트 중에는 ┤ 있다`()
    {
        //given

        //when
        val ladderPart = LadderPartFactory.createTurnLeft()

        //then
        assertEquals('┤', ladderPart.partChar)
    }

    @Test
    fun `사다리 파트 중에는 ━ 있다`()
    {
        //given

        //when
        val ladderPart = LadderPartFactory.createHorizontal()

        //then
        assertEquals('─', ladderPart.partChar)
    }


}