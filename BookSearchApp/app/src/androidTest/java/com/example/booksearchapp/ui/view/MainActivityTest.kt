package com.example.booksearchapp.ui.view

import android.view.*
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.*
import androidx.test.filters.*
import com.example.booksearchapp.R
import com.example.booksearchapp.ui.adapter.*
import com.google.common.truth.Truth.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.*
import org.junit.*

//RunWith를 사용하여 테스트를 실행하는 주체를 지정할 수 있다.
//AndroidJUnit4는 test runner이다. AndroidX Test Library가 Local test와 Instrumented Test에서 서로 다르게 동작할 수 있도록 도와준다.
//AndroidJUnit4 test runner 없이 AndroidX Test를 사용하면 제대로 동작하지 않을 가능성이 크다.
//@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
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
    var activityScenarioRule: ActivityScenarioRule<MainActivity>
        = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    @SmallTest
    fun test_activity_state() {
        val activityState = activityScenarioRule.scenario.state.name
        assertThat(activityState).isEqualTo("RESUMED")
    }


    @Test
    @LargeTest
    fun from_SearchFragment_to_FavoriteFragment_Ui_Operation() {
        //1. SearchFragment
        // 1-1. 리사이클러뷰 대신 "No Result"가 출력되는지 확인
        onView(withId(R.id.tv_emptyList))
            .check(matches(withText("No Result")))

        // 1-2) 검색어로 "android"를 입력
        onView(withId(R.id.et_search))
            .perform(typeText("android"))
        // 1-3) 리사이클러뷰 표시를 확인
        onView(isRoot()).perform(waitFor(3000))

        // 1-4) 첫 번째 반환값을 클릭
        onView(withId(R.id.rv_search_result))
            .check(matches(isDisplayed()))

        // 1-5) BookFragment 결과를 저장
        onView(withId(R.id.rv_search_result))
            .perform(actionOnItemAtPosition<BookSearchViewHolder>(0, click()))

        onView(withId(R.id.fab_favorite))
            .perform(click())
        // 1-6) 이전 화면으로 돌아감
        pressBack()
        // 1-7) SnackBar가 사라질 때까지 대기
        onView(isRoot()).perform(waitFor(3000))
        onView(withId(R.id.rv_search_result))
            .check(matches(isDisplayed()))

        //2. FavoriteFragment
        //2-1) FavoriteFragment로 이동
        onView(withId(R.id.fragment_favorite))
            .perform(click())
        //2-2) 리사이클러 뷰 표시를 확인
        onView(withId(R.id.rv_favorite_book))
            .check(matches(isDisplayed()))
        //2-3) 첫 번째 아이템을 슬라이드하여 삭제
        onView(withId(R.id.rv_favorite_book))
            .perform(actionOnItemAtPosition<BookSearchViewHolder>(0, swipeLeft()))

    }

    private fun waitFor(delay: Long) : ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "wait for $delay milliseconds"
            }

            override fun getConstraints(): Matcher<View> = isRoot()

            override fun perform(uiController: UiController?, view: View?) {
                uiController?.loopMainThreadForAtLeast(delay)
            }
        }
    }
}