package dev.jocatelo


class WinningBalls(originBalls:Balls, val bonus: Ball) : Balls {
    private var balls = originBalls

    override val size: Int
        get() = balls.size

    override fun contains(element: Ball): Boolean = balls.contains(element)

    override fun containsAll(elements: Collection<Ball>): Boolean = balls.containsAll(elements)

    override fun isEmpty(): Boolean = balls.isEmpty()

    override fun iterator(): Iterator<Ball> = balls.iterator()

    // Bulk Operations

    fun hasBonusBall(): Boolean {
        return true
    }

}