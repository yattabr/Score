package br.com.wobbu.score.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.wobbu.score.repository.APIClient

class MainViewModel : ViewModel() {

    val percentageObserver = MutableLiveData<Any>()

    fun fetchPercentage() {
        APIClient.getAPIInstance.fetchScore(percentageObserver)
    }

    fun percentageCalculator(score: Int, maxScore: Int): String {
        val div = (score.toDouble() / maxScore.toDouble()) * 100
        return "%.0f".format(div)
    }
}