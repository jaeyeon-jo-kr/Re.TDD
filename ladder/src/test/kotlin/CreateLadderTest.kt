import org.junit.Test
import kotlin.test.assertNotNull

class CreateLadderTest {
    @Test
    fun `사다리 제작자에게 사다리를 만들어달라고 요청하면 사다리를 만들어준다`()
    {
        val ladderCreator = LadderCreator()
        val ladder = ladderCreator.create()
        assertNotNull(ladder)
    }

    @Test
    fun `사다리 맵의 정보를 사다리 파트 제작자에게 주면 사다리 파트를 만들어준다`()
    {
        val ladderPartCreator = LadderPartCreator()
        val ladderCreator = LadderCreator()
        val ladderPartInfo = ladderCreator.createPartInfo()
        val part = ladderPartCreator.createPart(ladderPartInfo)
        assertNotNull(part)
    }


}