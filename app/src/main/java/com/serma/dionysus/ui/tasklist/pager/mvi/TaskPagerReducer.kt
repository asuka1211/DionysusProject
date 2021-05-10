package com.serma.dionysus.ui.tasklist.pager.mvi

import com.serma.dionysus.common.mvi.MviReducer
import javax.inject.Inject

class TaskPagerReducer @Inject constructor() : MviReducer<TaskPagerViewState, TaskPagerPartitionState> {

    override suspend fun reduce(
        lastViewState: TaskPagerViewState,
        partitionState: TaskPagerPartitionState
    ): TaskPagerViewState {
        return when (partitionState) {
            is TaskPagerPartitionState.Error -> reduceError(lastViewState, partitionState)
            TaskPagerPartitionState.Loading -> reduceLoading(lastViewState)
            is TaskPagerPartitionState.LoadingData -> reduceLoadingData(lastViewState, partitionState)
        }
    }

    private fun reduceError(
        lastViewState: TaskPagerViewState,
        state: TaskPagerPartitionState.Error
    ): TaskPagerViewState {
        return lastViewState.copy(error = Throwable(state.message), loading = false)
    }

    private fun reduceLoading(lastViewState: TaskPagerViewState,): TaskPagerViewState {
        return lastViewState.copy(error = null, loading = true)
    }

    private fun reduceLoadingData(
        lastViewState: TaskPagerViewState,
        state: TaskPagerPartitionState.LoadingData
    ): TaskPagerViewState {
        return lastViewState.copy(
            error = null,
            loading = false,
            data = state.data,
        )
    }
}