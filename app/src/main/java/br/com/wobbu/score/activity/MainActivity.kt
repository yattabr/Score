package br.com.wobbu.score.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.wobbu.score.R
import br.com.wobbu.score.model.ScoreResponse
import br.com.wobbu.score.viewmodel.MainViewModel
import com.github.kittinunf.fuel.core.FuelError
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        initObservers()
        mainViewModel.fetchPercentage()
    }

    private fun initUi(scoreResponse: ScoreResponse) {
        pie_score.minValue = 0f
        pie_score.maxValue = scoreResponse.creditReportInfo.maxScoreValue.toFloat()
        pie_score.setValue(scoreResponse.creditReportInfo.score.toFloat())

        txt_score.text = scoreResponse.creditReportInfo.score.toString()
        val maxScoreFormatted = "out of ${scoreResponse.creditReportInfo.maxScoreValue}"
        txt_max_score.text = maxScoreFormatted
        pb_loading.visibility = View.GONE

        val score = scoreResponse.creditReportInfo.score
        val maxScore = scoreResponse.creditReportInfo.maxScoreValue

        bt_percent.setOnClickListener { convertScorePercent(score, maxScore) }
    }

    private fun initObservers() {
        mainViewModel.percentageObserver.observe(this, Observer {
            when (it) {
                is ScoreResponse -> {
                    initUi(it)
                }
                is FuelError -> Log.i("FETCH_ERROR", it.message)
            }
        })
    }

    private fun convertScorePercent(score: Int, maxScore: Int) {
        val percent = "${mainViewModel.percentageCalculator(score, maxScore)}%"
        if (txt_score.text.contains("%")) {
            txt_score.text = score.toString()
        } else {
            txt_score.text = percent
        }

    }
}
