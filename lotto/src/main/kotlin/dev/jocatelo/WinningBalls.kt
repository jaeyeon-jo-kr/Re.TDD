package dev.jocatelo

class WinningBalls : Set<Int> {
    private val balls = hashSetOf<Int>()
    override fun contains(element: Int): Boolean = balls.contains(element)
    override fun containsAll(elements: Collection<Int>): Boolean = balls.containsAll(elements)
    override val size: Int = 0
    override fun isEmpty(): Boolean = true
    override fun iterator(): Iterator<Int> = balls.iterator()

    // Bulk Operations

    fun hasBonusBall(): Boolean? {
        return true
    }

    fun getBonusBall(): Any = 0
}