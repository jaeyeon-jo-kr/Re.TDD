package dev.jocatelo


class WinningLotto(origin:Lotto, val bonus: Ball) : Lotto,  RankChecker {
    private var lotto = origin

    override val size: Int
        get() = lotto.size

    override fun contains(element: Ball): Boolean = lotto.contains(element)

    override fun containsAll(elements: Collection<Ball>): Boolean = lotto.containsAll(elements)

    override fun isEmpty(): Boolean = lotto.isEmpty()

    override fun iterator(): Iterator<Ball> = lotto.iterator()

    // Bulk Operations

    fun hasBonusBall(): Boolean {
        return true
    }

    override fun askRank(clientLotto: Lotto): Int {
        when(clientLotto.filter { number -> lotto.contains(number) || number == bonus }.count()){
            6 -> return 1
            5 -> return 2
            4 -> return 3
            3 -> return 4
            2 -> return 5
        }
        return 0
    }

}