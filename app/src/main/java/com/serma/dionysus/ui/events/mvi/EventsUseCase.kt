package com.serma.dionysus.ui.events.mvi

import com.serma.dionysus.common.mvi.MviEffect
import com.serma.dionysus.common.mvi.MviUseCase
import com.serma.dionysus.domain.interactor.EventsInteractor
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@ExperimentalStdlibApi
class EventsUseCase @Inject constructor(
    private val interactor: EventsInteractor
) : MviUseCase<EventsPartitionState, EventsIntent, MviEffect>() {

    override suspend fun resolve(intent: EventsIntent): Flow<EventsPartitionState> {
        return when (intent) {
            is EventsIntent.Loading -> reduceLoading(intent)
            is EventsIntent.LoadingMore -> reduceLoadingMore(intent)
            is EventsIntent.Reload -> reduceReload(intent)
        }
    }

    private suspend fun reduceLoading(intent: EventsIntent.Loading): Flow<EventsPartitionState> {
        return interactor.load(intent.name, 0, intent.pageSize).map { result ->
            when (result) {
                is Result.Success -> EventsPartitionState.LoadingData(
                    intent.name,
                    result.data.list,
                    result.data.canLoading
                )
                is Result.Error -> EventsPartitionState.Error(result.message)
            }
        }.onStart { emit(EventsPartitionState.Loading) }
    }

    private suspend fun reduceLoadingMore(intent: EventsIntent.LoadingMore): Flow<EventsPartitionState> {
        return interactor.load(intent.name, intent.pageNumber).map { result ->
            when (result) {
                is Result.Success -> EventsPartitionState.LoadingMoreData(
                    intent.name,
                    result.data.list,
                    result.data.canLoading
                )
                is Result.Error -> EventsPartitionState.Error(result.message)
            }
        }.onStart { emit(EventsPartitionState.LoadingMore) }
    }

    private suspend fun reduceReload(intent: EventsIntent.Reload): Flow<EventsPartitionState> {
        return interactor.load(intent.name, 0).map { result ->
            when (result) {
                is Result.Success -> EventsPartitionState.LoadingData(
                    intent.name,
                    result.data.list,
                    result.data.canLoading
                )
                is Result.Error -> EventsPartitionState.Error(result.message)
            }
        }.onStart { emit(EventsPartitionState.Loading) }
    }
}