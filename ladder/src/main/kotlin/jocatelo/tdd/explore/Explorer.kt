package jocatelo.tdd.explore

import jocatelo.tdd.ladder.LadderMap
import jocatelo.tdd.ladder.LadderPart
import jocatelo.tdd.ladder.UnexpectedLadderPartException

class Explorer(val startColumn: Int = 0) {
    val result: Int = 0

    enum class Direction {
        LEFT, RIGHT, DOWN
    }

    fun explore(ladder: LadderMap) {

        var row = 0
        var col = startColumn
        var current = ladder.getPart(row, col)
        var direction = Direction.DOWN

        while (row < ladder.rowSize) {
            when (direction) {
                Direction.DOWN -> {
                    direction = when (current) {
                        LadderPart.TURN_LEFT -> {
                            col--
                            Direction.LEFT
                        }
                        LadderPart.TURN_RIGHT -> {
                            col++
                            Direction.RIGHT
                        }
                        LadderPart.DOWN -> {
                            row++
                            Direction.DOWN
                        }

                        else -> throw UnexpectedLadderPartException(
                            "Unexpected part at ($row, $col) direction : $direction, part : $current"
                        )
                    }
                }

                Direction.RIGHT -> {
                    col++
                    direction = when (current) {
                        LadderPart.HORIZONTAL ->
                            Direction.RIGHT
                        LadderPart.TURN_LEFT ->
                            Direction.DOWN
                        else -> throw UnexpectedLadderPartException(
                            "Unexpected part at ($row, $col) direction : $direction, part : $current"
                        )
                    }
                }
                Direction.LEFT -> {
                    col--
                    direction = when (current) {
                        LadderPart.HORIZONTAL ->
                            Direction.RIGHT
                        LadderPart.TURN_RIGHT ->
                            Direction.DOWN
                        else -> throw UnexpectedLadderPartException(
                            "Unexpected part at ($row, $col) direction : $direction, part : $current"
                        )
                    }
                }
            }
        }
    }


}
