package com.serma.dionysus.ui.graph

import androidx.lifecycle.viewModelScope
import com.serma.dionysus.common.mvi.simple.SimpleMviViewModel
import com.serma.dionysus.domain.interactor.GraphInteractor
import com.serma.dionysus.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GraphViewModel @Inject constructor(
    private val interactor: GraphInteractor
) : SimpleMviViewModel<GraphViewState>() {

    override fun createInitialState() = GraphViewState()

    fun loadBudget() {
        viewModelScope.launch {
            interactor.loadBudget().onStart { _uiState.emit(currentState.copy(loading = true)) }
                .collect {
                    when (it) {
                        is Result.Success -> _uiState.emit(
                            currentState.copy(
                                loading = false,
                                dataBudget = it.data
                            )
                        )
                        is Result.Error -> _uiState.emit(
                            currentState.copy(
                                loading = false,
                                dataBudget = null,
                                error = Throwable(it.message)
                            )
                        )
                    }
                }
        }
    }

    fun loadStatus() {
        viewModelScope.launch {
            interactor.loadStatus()
                .onStart { _uiState.emit(currentState.copy(loading = true)) }
                .collect {
                    when (it) {
                        is Result.Success -> _uiState.emit(
                            currentState.copy(
                                loading = false,
                                dataStatus = it.data
                            )
                        )
                        is Result.Error -> _uiState.emit(
                            currentState.copy(
                                loading = false,
                                dataStatus = null,
                                error = Throwable(it.message)
                            )
                        )
                    }
                }
        }
    }
}