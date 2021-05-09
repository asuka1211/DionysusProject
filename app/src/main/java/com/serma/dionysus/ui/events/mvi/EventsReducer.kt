package com.serma.dionysus.ui.events.mvi

import com.serma.dionysus.common.mvi.MviReducer
import com.serma.dionysus.common.ui.PagingItems
import javax.inject.Inject

class EventsReducer @Inject constructor() : MviReducer<EventsViewState, EventsPartitionState> {

    override suspend fun reduce(
        lastViewState: EventsViewState,
        partitionState: EventsPartitionState
    ): EventsViewState {
        return when (partitionState) {
            is EventsPartitionState.Error -> reduceError(lastViewState, partitionState)
            EventsPartitionState.Loading -> reduceLoading(lastViewState)
            is EventsPartitionState.LoadingData -> reduceLoadingData(lastViewState, partitionState)
            EventsPartitionState.LoadingMore -> reduceLoadingMore(lastViewState)
            is EventsPartitionState.LoadingMoreData -> reduceLoadingMoreData(
                lastViewState,
                partitionState
            )
        }
    }

    private fun reduceError(
        lastViewState: EventsViewState,
        state: EventsPartitionState.Error
    ): EventsViewState {
        return lastViewState.copy(error = Throwable(state.message), loading = false)
    }

    private fun reduceLoading(lastViewState: EventsViewState): EventsViewState {
        return lastViewState.copy(error = null, loading = true)
    }

    private fun reduceLoadingData(
        lastViewState: EventsViewState,
        state: EventsPartitionState.LoadingData
    ): EventsViewState {
        val items = state.items
        return lastViewState.copy(
            error = null,
            loading = false,
            events = PagingItems(items.size - 1, items),
            pageNumber = lastViewState.pageNumber + 1,
            searchText = state.name,
            loadingMore = false,
            canLoading = state.canLoading
        )
    }

    private fun reduceLoadingMore(
        lastViewState: EventsViewState,
    ): EventsViewState {
        return lastViewState.copy(error = null, loadingMore = true)
    }

    private fun reduceLoadingMoreData(
        lastViewState: EventsViewState,
        state: EventsPartitionState.LoadingMoreData
    ): EventsViewState {
        val items = (lastViewState.events?.items ?: listOf()) + state.items
        return lastViewState.copy(
            error = null,
            loadingMore = false,
            events = PagingItems(items.size - 1, items),
            pageNumber = lastViewState.pageNumber + 1,
            searchText = state.name,
            canLoading = state.canLoading
        )
    }
}