package jocatelo.tdd.ladder


import jocatelo.tdd.ladder.LadderPart as LadderPart


class LadderMap(val rowSize: Int = 7, val colSize: Int = 7) {

    private var ladder = Array(rowSize) { Array(colSize) { LadderPart.BLANK } }

    fun insertPart(row: Int, col: Int, ladderPart: LadderPart) {
        if (row < 0 || rowSize <= row)
            throw ArrayIndexOutOfBoundsException()

        if (col < 0 || colSize <= col)
            throw ArrayIndexOutOfBoundsException()

        ladder[row][col] = ladderPart
    }


    fun getPart(row: Int, col: Int): LadderPart {
        if (row < 0 || rowSize <= row)
            return LadderPart.BLANK

        if (col < 0 || colSize <= col)
            return LadderPart.BLANK

        return ladder[row][col]
    }

    override fun toString(): String {
        val ladderString = StringBuilder()
        repeat(rowSize)
        { row ->
            repeat(colSize)
            { col ->
                ladderString.append(ladder[row][col].block)
            }
            ladderString.append("\n")
        }
        return ladderString.toString()
    }


    fun getAllPartEvenColumns(): List<LadderPart> =
        ladder.flatten().filterIndexed { index, _ -> (index % rowSize) % 2 == 0 }.toList()

}
