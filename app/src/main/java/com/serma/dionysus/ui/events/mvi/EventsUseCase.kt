package com.serma.dionysus.ui.events.mvi

import com.serma.dionysus.common.mvi.MviEffect
import com.serma.dionysus.common.mvi.MviUseCase
import com.serma.dionysus.domain.interactor.EventsInteractor
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class EventsUseCase @Inject constructor(
    private val interactor: EventsInteractor
) : MviUseCase<EventsPartitionState, EventsIntent, MviEffect>() {

    override suspend fun resolve(intent: EventsIntent): Flow<EventsPartitionState> {
        return when (intent) {
            is EventsIntent.Loading -> reduceLoading(intent)
            is EventsIntent.LoadingMore -> reduceLoadingMore(intent)
            EventsIntent.Reload -> reduceReload()
        }
    }

    private suspend fun reduceLoading(intent: EventsIntent.Loading): Flow<EventsPartitionState> {
        return interactor.load(0, intent.pageSize).map { result ->
            when (result) {
                is Result.Success -> EventsPartitionState.LoadingData(result.data.list)
                is Result.Error -> EventsPartitionState.Error(result.message)
            }
        }.onStart { emit(EventsPartitionState.Loading) }
    }

    private suspend fun reduceLoadingMore(intent: EventsIntent.LoadingMore): Flow<EventsPartitionState> {
        return interactor.load(intent.pageNumber).map { result ->
            when (result) {
                is Result.Success -> EventsPartitionState.LoadingMoreData(result.data.list)
                is Result.Error -> EventsPartitionState.Error(result.message)
            }
        }.onStart { emit(EventsPartitionState.LoadingMore) }
    }

    private suspend fun reduceReload(): Flow<EventsPartitionState> {
        return interactor.load(0).map { result ->
            when (result) {
                is Result.Success -> EventsPartitionState.LoadingData(result.data.list)
                is Result.Error -> EventsPartitionState.Error(result.message)
            }
        }.onStart { emit(EventsPartitionState.Loading) }
    }

}