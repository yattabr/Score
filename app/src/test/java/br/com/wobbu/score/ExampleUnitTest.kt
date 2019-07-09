package br.com.wobbu.score

import br.com.wobbu.score.viewmodel.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {

    private val mainViewModel by lazy { MainViewModel() }

    @Test
    fun percent_calculation() {
        val percent = mainViewModel.percentageCalculator(320, 500)
        assertEquals(percent, "64")
    }
}
