package jocatelo.tdd.ladder


import kotlin.random.Random

class LadderPartCreator {

    private fun isFirstRowAndEvenCol(ladderInfo: LadderInfo): Boolean {
        if (isFirstRow(ladderInfo) && isEvenColumn(ladderInfo))
            return true
        return false
    }

    private fun isTurnLeftAvailable(ladderInfo: LadderInfo): Boolean {
        val ladderMap = ladderInfo.ladderMap

        if (isEvenColumn(ladderInfo)
            && ladderMap.getPart(ladderInfo.row, ladderInfo.col - 1) == LadderPart.BLANK
            && !isFirstRow(ladderInfo))
            return true
        return false
    }

    private fun isEvenColumn(ladderInfo: LadderInfo) = ladderInfo.col % 2 == 0

    private fun createLeftLadderPart(randomNumber: Int) : LadderPart
    {
        if (randomNumber % 2 == 0)
            return LadderPart.DOWN

        if (randomNumber % 2 == 1)
            return LadderPart.TURN_RIGHT

        return LadderPart.BLANK
    }

    fun createPart(ladderInfo: LadderInfo, randomNumber: Int = Random.nextInt()): LadderPart {

        val ladderMap = ladderInfo.ladderMap

        if (isFirstRowAndEvenCol(ladderInfo))
            return LadderPart.DOWN
        
        if (isTurnLeftAvailable(ladderInfo))
            return createLeftLadderPart(randomNumber)


        val prevCol = ladderInfo.col - 1
        val prevPart = ladderMap.getPart(ladderInfo.row, prevCol)

        if (prevPart == LadderPart.TURN_RIGHT)
            return LadderPartFactory.createHorizontal()

        if (prevPart == LadderPart.DOWN)
            return LadderPartFactory.createBlank()


        if (isFirstRow(ladderInfo) && prevPart == LadderPart.BLANK)
            return LadderPartFactory.createDown()

        return LadderPartFactory.createBlank()
    }

    private fun isFirstRow(ladderInfo: LadderInfo) = ladderInfo.row == 0

}
