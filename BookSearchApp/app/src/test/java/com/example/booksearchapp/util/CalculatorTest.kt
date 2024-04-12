package com.example.booksearchapp.util

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.*
import org.junit.After
import org.junit.Before
import org.junit.Test


//크기가 작은 테스트를 임명하기 위해 아래의 @SmallTest 추가
@SmallTest
class CalculatorTest {

    //private val calculator = Calculator()

    private lateinit var calculator: Calculator

    @Before
    fun setUp() {
        calculator = Calculator()
    }

    @After
    fun tearDown() {

    }

    //단위 테스트 진행
    //함수 이름에 백틱을 사용하면 문장처럼 사용할 수 있다. 한글도 가능
    @Test
    fun `additional function test`() {
        //given
        val x = 4
        val y = 2

        //when
        val result = calculator.addition(x, y)

        //then
        //truth 클래스에서 가지고 있는 assertThat을 사용
        assertThat(result).isEqualTo(6)
    }

    @Test
    fun `subtraction function test`() {
        //given
        val x = 4
        val y = 2

        //when
        val result = calculator.subtraction(x, y)

        //then
        //truth 클래스에서 가지고 있는 assertThat을 사용
        assertThat(result).isEqualTo(2)
    }

    @Test
    fun `multiple function test`() {
        //given
        val x = 4
        val y = 2

        //when
        val result = calculator.multiplication(x, y)

        //then
        //truth 클래스에서 가지고 있는 assertThat을 사용
        assertThat(result).isEqualTo(8)
    }

    @Test
    fun `divide function test`() {
        //given
        val x = 4
        val y = 2

        //when
        val result = calculator.division(x, y)

        //then
        //truth 클래스에서 가지고 있는 assertThat을 사용
        assertThat(result).isEqualTo(2)
    }

}