package dev.jocatelo

typealias Rank = Int
interface RankChecker {
    fun askRank(lotto: Lotto): Rank
}