package br.com.wobbu.score.repository

import androidx.lifecycle.MutableLiveData
import br.com.wobbu.score.BuildConfig
import br.com.wobbu.score.model.ScoreResponse
import com.github.kittinunf.fuel.Fuel

class APIClient {

    object getAPIInstance {
        fun fetchScore(observer: MutableLiveData<Any>) {
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