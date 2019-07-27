package jocatelo.tdd.ladder

class LadderCreator (private val rowSize:Int, private val colSize:Int){
    private val map: LadderMap = LadderMap(rowSize,colSize)
    private val creator = LadderPartCreator()

    fun create(): LadderMap {
        repeat(rowSize)
        {row:Int ->
            repeat(colSize){
                col ->
                val ladderInfo = LadderInfo(row,col,map)
                val part = creator.createPart(ladderInfo)
                map.insertPart(row, col, part)
            }
        }
        return map
    }

    fun createPartInfo(): LadderInfo {
        return LadderInfo(0, 0, map)
    }

}