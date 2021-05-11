package com.serma.dionysus.ui.task.mvi

import com.serma.dionysus.common.mvi.MviPartitionState
import com.serma.dionysus.ui.task.AddingTaskData

sealed class TaskPartitionState : MviPartitionState {

    object Loading : TaskPartitionState()
    data class LoadingData(val data: AddingTaskData) : TaskPartitionState()

    data class Error(val message: String) : TaskPartitionState()
}