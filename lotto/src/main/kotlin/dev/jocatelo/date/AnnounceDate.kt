package dev.jocatelo.date

import java.time.LocalDate
import java.util.*

//우승로또 공개 일자
class AnnounceDate(val announceDate:LocalDate) {

    constructor(announce:String) : this(LocalDate.parse(announce))

    init{

    }
}