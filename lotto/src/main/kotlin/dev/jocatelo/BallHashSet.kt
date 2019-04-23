package dev.jocatelo


class BallHashSet : Lotto {
    private val ballHashSet = hashSetOf<Ball>()

    override val size: Int
        get() = ballHashSet.size


    override fun contains(element: Ball): Boolean = ballHashSet.contains(element)

    override fun containsAll(elements: Collection<Ball>): Boolean = ballHashSet.containsAll(elements)

    override fun isEmpty(): Boolean = ballHashSet.isEmpty()

    override fun iterator(): Iterator<Ball> = ballHashSet.iterator()

    fun add(number: Ball) {
        ballHashSet += number
    }
}
