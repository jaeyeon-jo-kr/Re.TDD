package jocatelo.tdd.ladder

import java.sql.Time
import kotlin.random.Random

class LadderPartCreator {

    fun createPart(ladderInfo: LadderInfo, randomNumber: Int = Random.nextInt()): LadderPart {

        var ladderMap = ladderInfo.ladderMap
        var prevCol = ladderInfo.col - 1

        if(ladderInfo.col == 0 && ladderInfo.row == 0)
            return LadderPartFactory.createDown()

        var prevPart = ladderMap.getPart(ladderInfo.row, prevCol)

        if( 1 <= ladderInfo.row && ladderInfo.row < ladderMap.rowSize - 1 && ladderInfo.col == 0){
            if(randomNumber % 2 == 0)
                return LadderPartFactory.createDown()

            if(randomNumber % 2 == 1)
                return LadderPartFactory.createTurnRight()
        }

        if(prevPart.partChar == '├')
            return LadderPartFactory.createHorizontal()

        if(prevPart.partChar == '|')
            return LadderPartFactory.createBlank()


        if(ladderInfo.row == 0 && prevPart.partChar == ' ')
            return LadderPartFactory.createDown()

        return LadderPartFactory.createBlank()
    }

}
