package com.serma.dionysus.common.mvi.simple

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serma.dionysus.common.mvi.MviViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
abstract class SimpleMviViewModel<ViewState : MviViewState> : ViewModel() {

    private val initialState: ViewState by lazy { createInitialState() }

    abstract fun createInitialState(): ViewState

    val currentState: ViewState
        get() = uiState.value

    protected val _uiState: MutableStateFlow<ViewState> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()
}