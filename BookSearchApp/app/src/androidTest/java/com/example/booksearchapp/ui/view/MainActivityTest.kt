package com.example.booksearchapp.ui.view

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//RunWith를 사용하여 테스트를 실행하는 주체를 지정할 수 있다.
//AndroidJUnit4는 test runner이다. AndroidX Test Library가 Local test와 Instrumented Test에서 서로 다르게 동작할 수 있도록 도와준다.
//AndroidJUnit4 test runner 없이 AndroidX Test를 사용하면 제대로 동작하지 않을 가능성이 크다.
@RunWith(AndroidJUnit4::class)
class MainActivityTest{

//    private lateinit var activitySenario: ActivityScenario<MainActivity>
//
//    @Before
//    fun setUp() {
//        activitySenario = ActivityScenario.launch(MainActivity::class.java)
//    }
//
//    @After
//    fun tearDown(){
//        activitySenario.close()
//    }

    @get:Rule
    var activitySenarioRule: ActivityScenario<MainActivity>
        = ActivityScenario.launch(MainActivity::class.java)

    @Test
    @SmallTest
    fun test_activity_state() {
        val activityState = activitySenarioRule.state.name
        assertThat(activityState).isEqualTo("RESUMED")
    }
}