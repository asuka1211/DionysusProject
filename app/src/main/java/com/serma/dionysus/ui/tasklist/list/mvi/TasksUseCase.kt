package com.serma.dionysus.ui.tasklist.list.mvi

import com.serma.dionysus.common.mvi.MviEffect
import com.serma.dionysus.common.mvi.MviUseCase
import com.serma.dionysus.domain.interactor.TasksInteractor
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class TasksUseCase @Inject constructor(
    private val interactor: TasksInteractor
) : MviUseCase<TasksPartitionState, TasksIntent, MviEffect>() {

    override suspend fun resolve(intent: TasksIntent): Flow<TasksPartitionState> {
        return when (intent) {
            is TasksIntent.Loading -> reduceLoading(intent)
            is TasksIntent.Reload -> reduceReload(intent)
            is TasksIntent.Move -> reduceMove(intent)
        }
    }

    private suspend fun reduceLoading(intent: TasksIntent.Loading): Flow<TasksPartitionState> {
        return interactor.load(intent.eventId, intent.typeId).map { result ->
            when (result) {
                is Result.Success -> TasksPartitionState.LoadingData(intent.typeId, result.data)
                is Result.Error -> TasksPartitionState.Error(result.message)
            }
        }.onStart { emit(TasksPartitionState.Loading) }
    }

    private suspend fun reduceReload(intent: TasksIntent.Reload): Flow<TasksPartitionState> {
        return interactor.load(intent.eventId, intent.typeId).map { result ->
            when (result) {
                is Result.Success -> TasksPartitionState.LoadingData(intent.eventId, result.data)
                is Result.Error -> TasksPartitionState.Error(result.message)
            }
        }.onStart { emit(TasksPartitionState.Loading) }
    }

    private suspend fun reduceMove(intent: TasksIntent.Move): Flow<TasksPartitionState> {
        return interactor.move(intent.taskId, intent.nextId, intent.pageId).map { result ->
            when (result) {
                is Result.Success -> TasksPartitionState.LoadingData(intent.pageId, result.data)
                is Result.Error -> TasksPartitionState.Error(result.message)
            }
        }
    }

}