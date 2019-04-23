package dev.jocatelo

class LottoTicket {

    val lottoCount: Int
        get() = `lotto's`.size


    private var `lotto's` = hashSetOf<Lotto>()

    fun `generateLotto's`(lottoCount: Int) {
        val generator = LottoGenerator()
        repeat(lottoCount) {
            `lotto's`.add(generator.generateLotto())
        }
    }
}