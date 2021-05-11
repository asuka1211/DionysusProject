package com.serma.dionysus.ui.task.mvi

import com.serma.dionysus.common.mvi.MviReducer
import javax.inject.Inject

class TaskReducer @Inject constructor() : MviReducer<TaskViewState, TaskPartitionState> {

    override suspend fun reduce(
        lastViewState: TaskViewState,
        partitionState: TaskPartitionState
    ): TaskViewState {
        return when (partitionState) {
            is TaskPartitionState.Error -> reduceError(lastViewState, partitionState)
            TaskPartitionState.Loading -> reduceLoading(lastViewState)
            is TaskPartitionState.LoadingData -> reduceLoadingData(lastViewState, partitionState)
        }
    }

    private fun reduceError(
        lastViewState: TaskViewState,
        state: TaskPartitionState.Error
    ): TaskViewState {
        return lastViewState.copy(error = Throwable(state.message), loading = false)
    }

    private fun reduceLoading(lastViewState: TaskViewState,): TaskViewState {
        return lastViewState.copy(error = null, loading = true)
    }

    private fun reduceLoadingData(
        lastViewState: TaskViewState,
        state: TaskPartitionState.LoadingData
    ): TaskViewState {
        return lastViewState.copy(
            error = null,
            loading = false,
            data = state.data,
        )
    }
}