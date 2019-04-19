package dev.jocatelo


class BallHashSet : BallSet {
    private val ballHashSet:HashSet<Int>

    init {
        ballHashSet = hashSetOf<Int>()
    }

    override val size: Int
        get() = ballHashSet.size


    override fun contains(element: Int): Boolean {
        return ballHashSet.contains(element)
    }

    override fun containsAll(elements: Collection<Int>): Boolean {
        return ballHashSet.containsAll(elements)
    }

    override fun isEmpty(): Boolean {
        return ballHashSet.isEmpty()
    }

    override fun iterator(): Iterator<Int> {
        return ballHashSet.iterator()
    }

    fun add(number: Int) {
        ballHashSet.add(number)
    }
}
