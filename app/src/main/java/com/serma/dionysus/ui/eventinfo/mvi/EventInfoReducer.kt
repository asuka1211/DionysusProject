package com.serma.dionysus.ui.eventinfo.mvi

import com.serma.dionysus.common.mvi.MviReducer
import javax.inject.Inject

class EventInfoReducer @Inject constructor() : MviReducer<EventInfoViewState, EventInfoPartitionState> {

    override suspend fun reduce(
        lastViewState: EventInfoViewState,
        partitionState: EventInfoPartitionState
    ): EventInfoViewState {
        return when (partitionState) {
            is EventInfoPartitionState.Error -> reduceError(lastViewState, partitionState)
            EventInfoPartitionState.Loading -> reduceLoading(lastViewState)
            is EventInfoPartitionState.LoadingData -> reduceLoadingData(lastViewState, partitionState)
        }
    }

    private fun reduceError(
        lastViewState: EventInfoViewState,
        state: EventInfoPartitionState.Error
    ): EventInfoViewState {
        return lastViewState.copy(error = Throwable(state.message), loading = false)
    }

    private fun reduceLoading(lastViewState: EventInfoViewState,): EventInfoViewState {
        return lastViewState.copy(error = null, loading = true)
    }

    private fun reduceLoadingData(
        lastViewState: EventInfoViewState,
        state: EventInfoPartitionState.LoadingData
    ): EventInfoViewState {
        return lastViewState.copy(
            error = null,
            loading = false,
            data = state.data,
        )
    }
}