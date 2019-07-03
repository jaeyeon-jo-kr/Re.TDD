package jocatelo.tdd.ladder

import org.jetbrains.annotations.Nullable
import java.util.stream.IntStream.range

class LadderMap {
    private val ladder: HashMap<Int, HashMap<Int, LadderPart>> = hashMapOf()
    constructor(rowSize: Int = 5,
                colSize: Int = 5) {
        range(0, rowSize)
            .mapToObj { row ->
                ladder[row] = hashMapOf()
                ladder[row]
            }
            .forEach { ladderRow ->
                for (i in 0..colSize) {
                    ladderRow?.put(i, LadderPart())
                }}
    }

    fun insertPart(row: Int, col: Int, ladderPart: LadderPart) {
        if (ladder[row].isNullOrEmpty())
            ladder[row] = hashMapOf()
        ladder[row]!![col] = ladderPart
    }

    @Nullable
    fun getPart(row: Int, col: Int): LadderPart {
        if (ladder[row].isNullOrEmpty())
            ladder[row] = hashMapOf()

        return ladder[row]!![col]!!
    }
}
