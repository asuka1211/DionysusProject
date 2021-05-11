package com.serma.dionysus.ui.task.mvi

import com.serma.dionysus.common.mvi.MviEffect
import com.serma.dionysus.common.mvi.MviUseCase
import com.serma.dionysus.domain.interactor.TaskInteractor
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class TaskUseCase @Inject constructor(
    private val interactor: TaskInteractor
) : MviUseCase<TaskPartitionState, TaskIntent, MviEffect>() {

    override suspend fun resolve(intent: TaskIntent): Flow<TaskPartitionState> {
        return when (intent) {
            is TaskIntent.Loading -> reduceLoading(intent)
            is TaskIntent.Reload -> reduceReload(intent)
        }
    }

    private suspend fun reduceLoading(intent: TaskIntent.Loading): Flow<TaskPartitionState> {
        return interactor.load(intent.taskId).map { result ->
            when (result) {
                is Result.Success -> TaskPartitionState.LoadingData(result.data)
                is Result.Error -> TaskPartitionState.Error(result.message)
            }
        }.onStart { emit(TaskPartitionState.Loading) }
    }

    private suspend fun reduceReload(intent: TaskIntent.Reload): Flow<TaskPartitionState> {
        return interactor.load(intent.taskId).map { result ->
            when (result) {
                is Result.Success -> TaskPartitionState.LoadingData(result.data)
                is Result.Error -> TaskPartitionState.Error(result.message)
            }
        }.onStart { emit(TaskPartitionState.Loading) }
    }

}