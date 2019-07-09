package br.com.wobbu.score

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import br.com.wobbu.score.activity.MainActivity
import br.com.wobbu.score.model.CreditReport
import br.com.wobbu.score.model.ScoreResponse
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)
    private lateinit var scoreResponse: ScoreResponse

    @Before
    fun setup() {
        scoreResponse = ScoreResponse(CreditReport(320, "MATCH", 500))
    }

    @Test
    fun showScore() {
        activityTestRule.activity.mainViewModel.percentageObserver.postValue(scoreResponse)
        onView(allOf(withId(R.id.txt_score), (withText("320"))))
        onView(allOf(withId(R.id.txt_max_score), (withText("500"))))
    }

    @Test
    fun convertPercentage() {
        activityTestRule.activity.mainViewModel.percentageObserver.postValue(scoreResponse)
        onView(allOf(withId(R.id.txt_score), (withText("320"))))
        onView(allOf(withId(R.id.txt_max_score), (withText("500"))))

        onView(withId(R.id.bt_percent)).perform(click())

        onView(allOf(withId(R.id.txt_score), (withText("64%"))))

    }
}
