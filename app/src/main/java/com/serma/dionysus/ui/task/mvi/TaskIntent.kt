package com.serma.dionysus.ui.task.mvi

import com.serma.dionysus.common.mvi.MviIntent

sealed class TaskIntent : MviIntent {
    data class Loading(val taskId: String) : TaskIntent()
    data class Reload(val taskId: String) : TaskIntent()
}