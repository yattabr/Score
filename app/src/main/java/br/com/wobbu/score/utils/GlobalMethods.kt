package br.com.wobbu.score.utils

class GlobalMethods {
    companion object {
        val isRunningTest: Boolean by lazy {
            try {
                Class.forName("androidx.test.espresso.Espresso")
                true
            } catch (e: ClassNotFoundException) {
                false
            }
        }
    }
}