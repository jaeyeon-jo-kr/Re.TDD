package dev.jocatelo


data class Lotto(val ballSet:Set<Ball>) {

    init{
        if(ballSet.size != 6)
            throw InvalidSizeException()
    }
    val size: Int
        get() = ballSet.size

    fun contains(element: Any): Boolean = ballSet.contains(element)

    fun iterator(): Iterator<Ball> = ballSet.iterator()

    fun filter(param: (Ball) -> Boolean) : List<Ball> {
        return ballSet.filter(param)
    }

 }
