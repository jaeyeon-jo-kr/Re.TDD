package jocatelo.tdd.ladder

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class CreateLadderTest {
    @Test
    fun `사다리 제작자에게 사다리를 만들어달라고 요청하면 사다리를 만들어준다`()
    {
        // given
        val ladderCreator = LadderCreator()

        // when
        val ladder = ladderCreator.create()

        // then
        assertNotNull(ladder)
    }

    @Test
    fun `사다리 맵의 정보를 사다리 파트 제작자에게 주면 사다리 파트를 만들어준다`()
    {
        // given
        val ladderPartCreator = LadderPartCreator()
        val ladderCreator = LadderCreator()
        val ladderPartInfo = ladderCreator.createPartInfo()

        // when
        val part = ladderPartCreator.createPart(ladderPartInfo)

        // then
        assertNotNull(part)
    }

    @Test
    fun `사다리의 정보에서 좌표가 0,0일 때, '|'파트를 만들어준다`()
    {
        // given
        val ladderMap = LadderMap()
        val ladderInfo = LadderInfo(0, 0, ladderMap)
        val ladderPartCreator = LadderPartCreator()

        // when
        val ladderPart = ladderPartCreator.createPart(ladderInfo)

        // then
        assertEquals('│', ladderPart.block)
    }

    @Test
    fun `row 가 1이상 이고 마지막 row가 아니며 Col 이 0 일 때, 랜덤으로 '│' 파트를 만들어준다`()
    {
        // given
        val ladderMap = LadderMap()
        val ladderPartCreator = LadderPartCreator()
        val ladderInfo = LadderInfo(1, 0, ladderMap)

        // when
        val newLadderPart = ladderPartCreator.createPart(ladderInfo,0)

        // then
        assertEquals('│', newLadderPart.block)
    }

    @Test
    fun `row 가 1이상 이고 마지막 row가 아니며 Col 이 0 일 때, 랜덤으로 '├' 파트를 만들어준다`()
    {
        // given
        val ladderMap = LadderMap()

        val ladderPartCreator = LadderPartCreator()
        val ladderInfo = LadderInfo(1, 0, ladderMap)

        // when
        val newLadderPart = ladderPartCreator.createPart(ladderInfo, 1)

        // then
        assertEquals('├', newLadderPart.block)
    }

    @Test
    fun `col - 1 파트가 '├'일 때, '─'를 만들어준다`()
    {
        // given
        val ladderMap = LadderMap()
        val ladderPartCreator = LadderPartCreator()
        val previous = LadderPartFactory.createTurnRight()
        ladderMap.insertPart(1, 0, previous)
        val ladderInfo = LadderInfo(1, 1, ladderMap)

        // when
        val newLadderPart = ladderPartCreator.createPart(ladderInfo, 1)

        // then
        assertEquals('─', newLadderPart.block)
    }





    @Test
    fun `왼쪽 파트가 ' ' 이고 row가 0일 때, '│' 파트를 만들어준다`()
    {
        // given
        val ladderMap = LadderMap()
        val blank = LadderPartFactory.createBlank()
        ladderMap.insertPart(0, 1, blank)
        val ladderPartCreator = LadderPartCreator()

        val ladderInfo = LadderInfo(0, 2, ladderMap)

        // when
        val newLadderPart = ladderPartCreator.createPart(ladderInfo)

        // then
        assertEquals('│', newLadderPart.block)
    }




    //
    @Test
    fun `사다리 파트 중에는 내려가는 문자가 있다`()
    {
        // given

        // when
        val ladderPart = LadderPartFactory.createDown()

        // then
        assertEquals('│', ladderPart.block)
    }

    @Test
    fun `사다리 파트 중에는├ 있다`()
    {
        // given

        // when
        val ladderPart = LadderPartFactory.createTurnRight()

        // then
        assertEquals('├', ladderPart.block)
    }

    @Test
    fun `사다리 파트 중에는 ┤ 있다`()
    {
        // given

        // when
        val ladderPart = LadderPartFactory.createTurnLeft()

        // then
        assertEquals('┤', ladderPart.block)
    }

    @Test
    fun `사다리 파트 중에는 ━ 있다`()
    {
        // given

        // when
        val ladderPart = LadderPartFactory.createHorizontal()

        // then
        assertEquals('─', ladderPart.block)
    }


}