package jocatelo.tdd.ladder

class LadderPartCreator {
    fun createPart(ladderInfo: LadderInfo): LadderPart {

        var ladderMap = ladderInfo.ladderMap
        var prevCol = ladderInfo.col - 1

        if(ladderInfo.col == 0)
            return LadderPartFactory.createDown()

        var prevPart = ladderMap.getPart(ladderInfo.row, prevCol)

        if(prevPart.partChar == '|')
            return LadderPartFactory.createBlank()

        return LadderPartFactory.createBlank()
    }

}
