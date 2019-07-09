package br.com.wobbu.score.repository

import androidx.lifecycle.MutableLiveData
import br.com.wobbu.score.BuildConfig
import br.com.wobbu.score.model.ScoreResponse
import br.com.wobbu.score.utils.GlobalMethods
import com.github.kittinunf.fuel.Fuel

class APIClient {

    object getAPIInstance {
        private val isRunningTest: Boolean = GlobalMethods.isRunningTest
        fun fetchScore(observer: MutableLiveData<Any>) {
            if (!isRunningTest) {
                Fuel.get(BuildConfig.SERVER_URL)
                    .responseObject(ScoreResponse.Deserializer()) { _, _, result ->
                        val (data, error) = result
                        if (data != null) {
                            observer.postValue(data)
                        } else {
                            observer.postValue(error)
                        }
                    }
            }
        }
    }

}