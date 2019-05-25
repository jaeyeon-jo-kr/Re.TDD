package dev.jocatelo

import dev.jocatelo.winninglotto.WinningLottoGenerator
import dev.jocatelo.winninglotto.WinningLottoLibrary
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNot
import org.hamcrest.core.IsNull
import org.junit.Test

class TestWinningLottoLibrary {

    @Test
    fun `로또 보관소는 1회차 당첨 로또 번호를 가지고 있다`()
    {
        WinningLottoLibrary.setLotto(1, WinningLottoGenerator.generateLotto())
        assertThat("로또번호 설정", WinningLottoLibrary.getLotto(1), IsNot(IsNull()))
    }

}