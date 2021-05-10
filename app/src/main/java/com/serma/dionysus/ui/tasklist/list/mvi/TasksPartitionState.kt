package com.serma.dionysus.ui.tasklist.list.mvi

import com.serma.dionysus.common.mvi.MviPartitionState
import com.serma.dionysus.ui.tasklist.list.TasksTypeData

sealed class TasksPartitionState : MviPartitionState {

    object Loading : TasksPartitionState()
    data class LoadingData(val typeId: String, val data: TasksTypeData) : TasksPartitionState()

    data class Error(val message: String) : TasksPartitionState()
}