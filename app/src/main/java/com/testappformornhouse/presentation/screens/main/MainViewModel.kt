package com.testappformornhouse.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.testappformornhouse.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {


    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState(("")))
    val uiState: StateFlow<MainUiState> = _uiState
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
        _uiState.update {
            it.copy(
                number = num
            )
        }
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