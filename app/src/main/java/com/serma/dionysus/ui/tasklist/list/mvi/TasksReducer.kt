package com.serma.dionysus.ui.tasklist.list.mvi

import com.serma.dionysus.common.mvi.MviReducer
import javax.inject.Inject

class TasksReducer @Inject constructor() : MviReducer<TasksViewState, TasksPartitionState> {

    override suspend fun reduce(
        lastViewState: TasksViewState,
        partitionState: TasksPartitionState
    ): TasksViewState {
        return when (partitionState) {
            is TasksPartitionState.Error -> reduceError(lastViewState, partitionState)
            TasksPartitionState.Loading -> reduceLoading(lastViewState)
            is TasksPartitionState.LoadingData -> reduceLoadingData(lastViewState, partitionState)
        }
    }

    private fun reduceError(
        lastViewState: TasksViewState,
        state: TasksPartitionState.Error
    ): TasksViewState {
        return lastViewState.copy(error = Throwable(state.message), loading = false)
    }

    private fun reduceLoading(lastViewState: TasksViewState): TasksViewState {
        return lastViewState.copy(error = null, loading = true)
    }

    private fun reduceLoadingData(
        lastViewState: TasksViewState,
        state: TasksPartitionState.LoadingData
    ): TasksViewState {
        val map = lastViewState.data.toMutableMap()
        map[state.typeId] = state.data
        return lastViewState.copy(
            error = null,
            loading = false,
            data = map,
        )
    }
}