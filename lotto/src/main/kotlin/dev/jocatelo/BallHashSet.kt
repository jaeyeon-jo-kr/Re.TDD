package dev.jocatelo


class BallHashSet(balls : HashSet<Int>) : BallSet {
    override val number:Int = balls.size
}