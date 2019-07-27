package jocatelo.tdd.ladder


import kotlin.math.absoluteValue
import kotlin.random.Random

class LadderPartCreator {

    var random  = Random.nextInt().absoluteValue

    private fun isEvenColumn(ladderInfo: LadderInfo) = ladderInfo.col % 2 == 0

    private fun createLeftLadderPart() : LadderPart
    {
        var part = LadderPart.DOWN

        if (random % 2 == 1)
            part = LadderPart.TURN_RIGHT

        random = Random.nextInt().absoluteValue
        return part
    }

    private fun getPreviousPart(info:LadderInfo) : LadderPart
    {
        val map = info.ladderMap
        return map.getPart(info.row, info.col - 1)
    }

    fun createPart(ladderInfo: LadderInfo): LadderPart {

        if(isEvenColumn(ladderInfo))
            return createVerticalPart(ladderInfo)
        return createHorizontalPart(ladderInfo)
    }

    private fun createHorizontalPart(info: LadderInfo): LadderPart {
        val previous = getPreviousPart(info)
        if (previous == LadderPart.TURN_RIGHT)
            return LadderPart.HORIZONTAL
        return LadderPart.BLANK
    }

    private fun createVerticalPart(info: LadderInfo): LadderPart {
        if (isFirstRow(info))
            return LadderPart.DOWN

        if(isLastRow(info))
            return LadderPart.DOWN

        if(isFirstCol(info))
            return createLeftLadderPart()

        val previous = getPreviousPart(info)

        if(previous == LadderPart.HORIZONTAL)
            return LadderPart.TURN_LEFT

        if(isLastCol(info))
            return LadderPart.DOWN

        return createLeftLadderPart()
    }

    private fun isLastCol(info: LadderInfo) = info.col == info.ladderMap.colSize - 1
    private fun isFirstCol(info: LadderInfo) = info.col == 0
    private fun isFirstRow(info: LadderInfo) = info.row == 0
    private fun isLastRow(info: LadderInfo) = info.row == info.ladderMap.rowSize - 1

}
