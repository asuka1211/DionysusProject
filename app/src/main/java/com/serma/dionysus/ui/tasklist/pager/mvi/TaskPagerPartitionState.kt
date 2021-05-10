package com.serma.dionysus.ui.tasklist.pager.mvi

import com.serma.dionysus.common.mvi.MviPartitionState
import com.serma.dionysus.ui.profile.ProfileData
import com.serma.dionysus.ui.tasklist.pager.TaskPagerData

sealed class TaskPagerPartitionState : MviPartitionState {

    object Loading : TaskPagerPartitionState()
    data class LoadingData(val data: TaskPagerData) : TaskPagerPartitionState()

    data class Error(val message: String) : TaskPagerPartitionState()
}