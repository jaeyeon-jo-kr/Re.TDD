package dev.jocatelo

interface PrizeChecker {
    fun expectedPrize(rankChecker: RankChecker): Int
}