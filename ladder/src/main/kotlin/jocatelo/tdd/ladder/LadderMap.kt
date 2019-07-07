package jocatelo.tdd.ladder

import org.jetbrains.annotations.Nullable
import jocatelo.tdd.ladder.LadderPart as LadderPart


class LadderMap(val rowSize: Int = 5, private val colSize: Int = 5) {

    private var ladder = Array(rowSize){Array(colSize){LadderPart.BLANK}}

    fun insertPart(row: Int, col: Int, ladderPart: LadderPart) {
        if(row < 0 || rowSize <= row)
            throw ArrayIndexOutOfBoundsException()

        if(col < 0 || colSize <= col)
            throw ArrayIndexOutOfBoundsException()

        ladder[row][col] = ladderPart
    }

    @Nullable
    fun getPart(row: Int, col: Int): LadderPart? = ladder[row]!![col]!!



}
