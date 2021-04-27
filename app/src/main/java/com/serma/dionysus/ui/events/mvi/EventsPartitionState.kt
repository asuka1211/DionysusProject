package com.serma.dionysus.ui.events.mvi

import com.serma.dionysus.common.mvi.MviPartitionState
import com.serma.dionysus.ui.events.EventData

sealed class EventsPartitionState : MviPartitionState {
    object Loading : EventsPartitionState()
    data class LoadingData(val items: List<EventData>) : EventsPartitionState()

    object LoadingMore : EventsPartitionState()
    data class LoadingMoreData(val items: List<EventData>) : EventsPartitionState()

    data class Error(val message: String) : EventsPartitionState()
}