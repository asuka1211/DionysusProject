package com.serma.dionysus.ui.tasklist.pager.mvi

import com.serma.dionysus.common.mvi.MviEffect
import com.serma.dionysus.common.mvi.MviUseCase
import com.serma.dionysus.domain.interactor.ProfileInteractor
import com.serma.dionysus.domain.interactor.TaskPagerInteractor
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class TaskPagerUseCase @Inject constructor(
    private val interactor: TaskPagerInteractor
) : MviUseCase<TaskPagerPartitionState, TaskPagerIntent, MviEffect>() {

    override suspend fun resolve(intent: TaskPagerIntent): Flow<TaskPagerPartitionState> {
        return when (intent) {
            is TaskPagerIntent.Loading -> reduceLoading(intent)
            is TaskPagerIntent.Reload -> reduceReload(intent)
        }
    }

    private suspend fun reduceLoading(intent: TaskPagerIntent.Loading): Flow<TaskPagerPartitionState> {
        return interactor.load(intent.eventId).map { result ->
            when (result) {
                is Result.Success -> TaskPagerPartitionState.LoadingData(result.data)
                is Result.Error -> TaskPagerPartitionState.Error(result.message)
            }
        }.onStart { emit(TaskPagerPartitionState.Loading) }
    }

    private suspend fun reduceReload(intent: TaskPagerIntent.Reload): Flow<TaskPagerPartitionState> {
        return interactor.load(intent.eventId).map { result ->
            when (result) {
                is Result.Success -> TaskPagerPartitionState.LoadingData(result.data)
                is Result.Error -> TaskPagerPartitionState.Error(result.message)
            }
        }.onStart { emit(TaskPagerPartitionState.Loading) }
    }

}