package com.serma.dionysus.ui.events

import androidx.lifecycle.viewModelScope
import com.serma.dionysus.common.mvi.MviEffect
import com.serma.dionysus.common.mvi.MviViewModel
import com.serma.dionysus.ui.events.mvi.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    reducer: EventsReducer,
    useCase: EventsUseCase
) : MviViewModel<MviEffect, EventsIntent, EventsViewState, EventsPartitionState>(
    reducer, useCase
) {

    override fun createInitialState() = EventsViewState()

    init {
        load()
    }

    fun loadMore(pageNumber: Int, pageSize: Int = 50) {
        viewModelScope.launch {
            sendIntent(EventsIntent.LoadingMore(pageNumber, pageSize))
        }
    }

    private fun load(pageSize: Int = 50) {
        viewModelScope.launch {
            sendIntent(EventsIntent.Loading(pageSize))
        }
    }

    fun reload() {
        viewModelScope.launch {
            sendIntent(EventsIntent.Reload)
        }
    }
}