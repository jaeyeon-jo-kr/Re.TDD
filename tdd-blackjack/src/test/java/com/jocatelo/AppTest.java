/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.jocatelo;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * 1. 딜러와 플레이어
 * 2. 카드 2장을 기본적으로 지급받기
 * 2. 21에 가까운 수를 만들기
 * 3. 21을 초과하면 진다.  * 
 * 4. 배팅금액을 정하기
 * 5. 히트
 * 6. 스탠드, 스테이
 * 7. 스플릿
 * 8. 더블 다운
 * 9. 버스트
 * 10. Ace 한장과 10에 해당하는 패로 21을 이루는 경우 베팅금액의 1.5배를 돌려준다.
 */
public class AppTest {
    @Test public void testAppHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
}
