package jocatelo.tdd.ladder

class LadderCreator (private val ladderMap: LadderMap = LadderMap(5,5)) {

    fun create(): Any {
        return Any()
    }

    fun createPartInfo(): LadderInfo {
        return LadderInfo(0, 0, LadderMap())
    }

}