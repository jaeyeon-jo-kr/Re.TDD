package dev.jocatelo

class LottoTicket : Set<Lotto> {
    override fun contains(element: Lotto): Boolean = `lotto's`.contains(element)
    override fun containsAll(elements: Collection<Lotto>): Boolean = `lotto's`.containsAll(elements)
    override fun isEmpty(): Boolean = `lotto's`.isEmpty()
    override fun iterator(): Iterator<Lotto> = `lotto's`.iterator()

    override val size: Int
        get() = `lotto's`.size

    private var `lotto's` = hashSetOf<Lotto>()

    fun `generateLotto's`(lottoCount: Int) {
        val generator = LottoGenerator()
        repeat(lottoCount) {
            `lotto's`.add(generator.generateLotto())
        }
    }
}