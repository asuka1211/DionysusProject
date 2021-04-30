package com.serma.dionysus.ui.eventinfo.mvi

import com.serma.dionysus.common.mvi.MviEffect
import com.serma.dionysus.common.mvi.MviUseCase
import com.serma.dionysus.domain.interactor.EventInfoInteractor
import com.serma.dionysus.domain.interactor.ProfileInteractor
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class EventInfoUseCase @Inject constructor(
    private val interactor: EventInfoInteractor
) : MviUseCase<EventInfoPartitionState, EventInfoIntent, MviEffect>() {

    override suspend fun resolve(intent: EventInfoIntent): Flow<EventInfoPartitionState> {
        return when (intent) {
            is EventInfoIntent.Loading -> reduceLoading(intent)
            is EventInfoIntent.Reload -> reduceReload(intent)
        }
    }

    private suspend fun reduceLoading(intent: EventInfoIntent.Loading): Flow<EventInfoPartitionState> {
        return interactor.load(intent.eventId).map { result ->
            when (result) {
                is Result.Success -> EventInfoPartitionState.LoadingData(result.data)
                is Result.Error -> EventInfoPartitionState.Error(result.message)
            }
        }.onStart { emit(EventInfoPartitionState.Loading) }
    }

    private suspend fun reduceReload(intent: EventInfoIntent.Reload): Flow<EventInfoPartitionState> {
        return interactor.load(intent.eventId).map { result ->
            when (result) {
                is Result.Success -> EventInfoPartitionState.LoadingData(result.data)
                is Result.Error -> EventInfoPartitionState.Error(result.message)
            }
        }.onStart { emit(EventInfoPartitionState.Loading) }
    }

}