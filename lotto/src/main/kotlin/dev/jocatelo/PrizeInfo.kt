package dev.jocatelo

class PrizeInfo {
    fun getPrize(rank: Int): Int {
        return when (rank) {
            1 -> 1_000_000
            2 -> 500_000
            3 -> 300_000
            4 -> 50_000
            5 -> 5_000
            else -> 0
        }
    }

}
