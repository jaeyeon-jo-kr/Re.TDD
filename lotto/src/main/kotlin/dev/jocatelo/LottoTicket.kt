package dev.jocatelo

class LottoTicket {

    val lottoCount: Int
        get() {
            return `lotto's`.size
        }

    private var `lotto's` = hashSetOf<Lotto>()

    fun `generateLotto's`(lottoCount: Int, generator: LottoGenerator) {
        repeat(lottoCount) {
            `lotto's`.add(generator.generateBalls())
        }
    }
}