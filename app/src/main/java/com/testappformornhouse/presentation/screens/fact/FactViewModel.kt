package com.testappformornhouse.presentation.screens.fact

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testappformornhouse.data.Repository
import com.testappformornhouse.presentation.screens.main.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(
    repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState: MutableStateFlow<FactUiState> = MutableStateFlow(FactUiState("",""))
    val uiState: StateFlow<FactUiState> = _uiState


    init {
        val number = savedStateHandle.get<String>("numberFromScreen").toString()
        viewModelScope.launch(Dispatchers.IO) {
            if (number == "null"){
                _uiState.update {
                    it.copy(
                        number = number,
                        factAboutNumber = repository.getFactForRandomNumber()
                    )
                }
            }
            else{
                _uiState.update {
                    it.copy(
                        number = number,
                        factAboutNumber = repository.getFactForNumber(number)
                    )
                }
            }
        }

    }

}

data class FactUiState(
    var number: String,
    var factAboutNumber: String
)