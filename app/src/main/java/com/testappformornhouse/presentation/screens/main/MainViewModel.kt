package com.testappformornhouse.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testappformornhouse.data.Repository
import com.testappformornhouse.data.local.FactsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {


    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState(("")))
    val uiState: StateFlow<MainUiState> = _uiState

    fun updateDatabase(){
        viewModelScope.launch(Dispatchers.IO){
            _uiState.update {
                it.copy(
                    listOfFacts = repository.getAllFacts()
                )
            }
        }
    }
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
    val number: String,
    val listOfFacts: List<FactsEntity> = listOf<FactsEntity>()
)

sealed interface MainEvent {
    data object GetFact : MainEvent
    data object GetFactRandom : MainEvent

    class ChangeNumber(val num: String) : MainEvent

}