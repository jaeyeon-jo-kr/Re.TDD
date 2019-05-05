package dev.jocatelo.ui


enum class UIString(var screen: String) {

    MAIN_MENU("""==== Lotto ====
                                           |1. Ticket 정보 보기
                                           |2. Ticket 만들기
                                           |3. 등수 확인
                                           |4. 종료하기
                                           |명령>
                                          """.trimMargin("|"));

    override fun toString(): String {
        return screen
    }

}