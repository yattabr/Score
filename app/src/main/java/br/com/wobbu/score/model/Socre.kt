package br.com.wobbu.score.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

class ScoreResponse(var creditReportInfo: CreditReport) {
    class Deserializer : ResponseDeserializable<ScoreResponse> {
        override fun deserialize(content: String): ScoreResponse? {
            val gson = Gson().newBuilder().serializeNulls().create()
            return gson.fromJson(content, ScoreResponse::class.java)
        }
    }
}

data class CreditReport(var score: Int, var status: String, var maxScoreValue: Int)