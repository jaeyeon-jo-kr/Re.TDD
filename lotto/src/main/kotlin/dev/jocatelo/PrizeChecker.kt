package dev.jocatelo

//TODO:PrizeChecker 와 Rank Checer는 함께 움직이므로 Checker로 통합하는 게 좋다.
interface PrizeChecker {
    fun expectedPrize(rank: Rank): Int
}