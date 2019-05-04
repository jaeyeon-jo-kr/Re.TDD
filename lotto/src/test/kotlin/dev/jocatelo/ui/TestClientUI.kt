package dev.jocatelo.ui

import junit.framework.TestCase.assertEquals
import org.junit.Test

//메뉴 목록은 다음과 같다.
//Ticket 정보 보기
//Ticket 만들기
//등수 확인
//종료하기
class TestClientUI
{
    @Test
    fun `사용자는 프로그램을 실행하면 메뉴를 확인한다`()
    {
        assertEquals(UIString.MAIN_MENU.toString(), LottoMain.screen)
    }

    @Test
    fun `사용자는 첫 메뉴에서 종료하기를 입력하면 프로그램을 종료한다`()
    {
        LottoMain.processInput("4")
        assertEquals(false, LottoMain.running)
    }

}