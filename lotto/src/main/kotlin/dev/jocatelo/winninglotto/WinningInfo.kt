package dev.jocatelo.winninglotto

import dev.jocatelo.date.AnnounceDate
import java.time.LocalDate
import java.time.LocalDate.*

class WinningInfo(val winningLotto:WinningLotto,
                  var drawDate: AnnounceDate = AnnounceDate(now()),
                  var round:Int = 0) {

    init{

    }

}