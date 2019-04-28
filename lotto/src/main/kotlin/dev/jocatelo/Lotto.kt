package dev.jocatelo


class Lotto {
    private val ballHashSet = hashSetOf<Ball>()

    val size: Int
        get() = ballHashSet.size

    fun contains(element: Any): Boolean = ballHashSet.contains(element)
    

//    override fun containsAll(elements: Collection<Ball>): Boolean = ballHashSet.containsAll(elements)
//
//    override fun isEmpty(): Boolean = ballHashSet.isEmpty()
//
    fun iterator(): Iterator<Ball> = ballHashSet.iterator()

    fun add(number: Ball) {
        ballHashSet += number
    }

    fun filter(param: (Ball) -> Boolean) : List<Ball> {
        return ballHashSet.filter(param)
    }

    fun toList(): List<Ball> {
        return ballHashSet.toList()
    }
}
