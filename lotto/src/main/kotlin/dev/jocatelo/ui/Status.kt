package dev.jocatelo.ui

import dev.jocatelo.Client


enum class Status {
    MAIN_MENU
    {
        override fun screen() = """==== Lotto ====
                |1. Ticket 정보 보기
                |2. Ticket 만들기
                |3. 등수 확인
                |4. 종료하기
                |명령>
                """.trimMargin("|")
    }, TICKET_INFO
    {
        override fun screen(): String
        {
            val lottoList = LottoMain.client.ticketSet.joinToString()
            val background = """===== Ticket 정보 =====
            |Ticket 구매자 :
            |%BALL_LIST%
            """.trimIndent()
            return background.replace("%BALL_LIST%", lottoList)
        }
    };
    abstract fun screen():String
}