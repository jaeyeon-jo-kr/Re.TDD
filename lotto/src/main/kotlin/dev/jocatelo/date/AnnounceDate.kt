package dev.jocatelo.date

import java.time.LocalDate
import java.util.*

//우승로또 공개 일자
class AnnounceDate(val announceDate:LocalDate) {

    constructor(announce:String) : this(LocalDate.parse(announce))

    init{

    }

    override operator fun equals(other: Any?) :Boolean {

        if(other is AnnounceDate)
        {
            val expected: AnnounceDate = other
            val expectedAnnounceDate = expected.announceDate
            if(expectedAnnounceDate.year == announceDate.year &&
                        expectedAnnounceDate.month == announceDate.month &&
                        expectedAnnounceDate.dayOfMonth == announceDate.dayOfMonth)
                return true
        }
        return false
    }
}