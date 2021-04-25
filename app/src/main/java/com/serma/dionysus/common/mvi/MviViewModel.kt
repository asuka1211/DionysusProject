package com.serma.dionysus.common.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
abstract class MviViewModel<Effect : MviEffect, Intent : MviIntent,
        ViewState : MviViewState, PartitionState : MviPartitionState>(
    private val reducer: MviReducer<ViewState, PartitionState>,
    private val useCase: MviUseCase<PartitionState, Intent, Effect>
) : ViewModel() {

    private val initialState: ViewState by lazy { createInitialState() }
    abstract fun createInitialState(): ViewState

    val currentState: MviViewState
        get() = uiState.value

    private val _uiState: MutableStateFlow<ViewState> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()
    val intent = _intent.asSharedFlow()

    private val _effect: Channel<MviEffect> = Channel()
    val effect = _effect.receiveAsFlow()


    init {
        subscribeEvents()
    }

    private fun subscribeEvents() {
        viewModelScope.launch {
            intent
                .flatMapConcat { useCase.resolve(it) }
                .scan(initialState, reducer::reduce)
                .collect { _uiState.value = it }

            useCase.effectSubscription.collect { _effect.send(it) }
        }
    }

    fun sendIntent(intent: Intent) {
        viewModelScope.launch { _intent.emit(intent) }
    }
}