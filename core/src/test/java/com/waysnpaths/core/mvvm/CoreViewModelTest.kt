package com.waysnpaths.core.mvvm

import org.junit.Test

class CoreViewModelTest {

    data class Model (val test: Int)
    class ViewModel : CoreViewModel<Model>()

    private val viewModel = ViewModel()

    @Test
    fun getModel() {
    }

    @Test
    fun onCleared() {
    }
}