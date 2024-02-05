package com.testappformornhouse.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.testappformornhouse.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    fun obtainEvent(event: MainEvent) {
        when (event) {
            is MainEvent.GetFact -> {
                getFact()
            }

            is MainEvent.GetFactRandom -> {
                getFactRandom()
            }

            is MainEvent.ChangeNumber -> {
                changeNumber(event.num)
            }
        }
    }

    private fun getFact() {

    }

    private fun getFactRandom() {

    }

    private fun changeNumber(num: String) {

    }

}

data class MainUiState(
    var number: String
)

sealed interface MainEvent {
    data object GetFact : MainEvent
    data object GetFactRandom : MainEvent

    class ChangeNumber(val num: String) : MainEvent

}