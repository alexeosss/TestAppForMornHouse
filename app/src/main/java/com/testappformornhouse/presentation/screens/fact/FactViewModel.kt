package com.testappformornhouse.presentation.screens.fact

import android.text.TextUtils.split
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testappformornhouse.data.Repository
import com.testappformornhouse.data.local.FactsEntity
import com.testappformornhouse.presentation.screens.main.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.URLDecoder
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(
    repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState: MutableStateFlow<FactUiState> = MutableStateFlow(FactUiState("", ""))
    val uiState: StateFlow<FactUiState> = _uiState


    init {
        val number = URLDecoder.decode(savedStateHandle.get<String>("numberFromScreen").toString())

        viewModelScope.launch(Dispatchers.IO) {
            when {
                number == "null" -> {
                    val fact = repository.getFactForRandomNumber()
                    _uiState.update {
                        it.copy(
                            number = fact.split(" ").first(),
                            factAboutNumber = fact.also { fact ->
                                repository.putFact(FactsEntity(fact = fact))
                            }
                        )
                    }
                }

                number.split(" ").size > 1 -> {
                    _uiState.update {
                        it.copy(
                            number = number.split(" ").first(),
                            factAboutNumber = number
                        )
                    }
                }

                else -> {
                    _uiState.update {
                        it.copy(
                            number = number,
                            factAboutNumber = repository.getFactForNumber(number).also { fact ->
                                repository.putFact(FactsEntity(fact = fact))
                            }
                        )
                    }
                }

            }
        }
    }

}

data class FactUiState(
    var number: String,
    var factAboutNumber: String
)