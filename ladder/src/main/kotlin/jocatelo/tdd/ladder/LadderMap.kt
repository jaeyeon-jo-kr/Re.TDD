package jocatelo.tdd.ladder

import org.jetbrains.annotations.Nullable
import java.util.stream.IntStream.range

class LadderMap(val rowSize: Int = 5, val colSize: Int = 5) {
    private val ladder: HashMap<Int, HashMap<Int, LadderPart>> = hashMapOf()

    init {
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
        if(col < 0)
            return LadderPartFactory.createBlank()

        if (ladder[row].isNullOrEmpty())
            ladder[row] = hashMapOf()

        return ladder[row]!![col]!!
    }
}
