package com.serma.dionysus.ui.eventinfo

import androidx.lifecycle.viewModelScope
import com.serma.dionysus.common.mvi.MviEffect
import com.serma.dionysus.common.mvi.MviViewModel
import com.serma.dionysus.ui.eventinfo.mvi.EventInfoIntent
import com.serma.dionysus.ui.eventinfo.mvi.EventInfoPartitionState
import com.serma.dionysus.ui.eventinfo.mvi.EventInfoReducer
import com.serma.dionysus.ui.eventinfo.mvi.EventInfoUseCase
import com.serma.dionysus.ui.eventinfo.mvi.EventInfoViewState
import com.serma.dionysus.ui.events.mvi.*
import com.serma.dionysus.ui.profile.mvi.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventInfoViewModel @Inject constructor(
    reducer: EventInfoReducer,
    useCase: EventInfoUseCase
) : MviViewModel<MviEffect, EventInfoIntent, EventInfoViewState, EventInfoPartitionState>(
    reducer, useCase
) {

    override fun createInitialState() = EventInfoViewState()

    fun load(eventId: String) {
        viewModelScope.launch {
            sendIntent(EventInfoIntent.Loading(eventId))
        }
    }

    fun reload(eventId: String) {
        viewModelScope.launch {
            sendIntent(EventInfoIntent.Reload(eventId))
        }
    }

}