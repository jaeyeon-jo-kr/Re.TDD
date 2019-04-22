package dev.jocatelo


class BallHashSet : Lotto {
    private val ballHashSet = hashSetOf<Ball>()

    override val size: Int
        get() = ballHashSet.size


    override fun contains(element: Ball): Boolean {
        return ballHashSet.contains(element)
    }

    override fun containsAll(elements: Collection<Ball>): Boolean {
        return ballHashSet.containsAll(elements)
    }

    override fun isEmpty(): Boolean {
        return ballHashSet.isEmpty()
    }

    override fun iterator(): Iterator<Ball> {
        return ballHashSet.iterator()
    }

    fun add(number: Ball) {
        ballHashSet.add(number)
    }
}
