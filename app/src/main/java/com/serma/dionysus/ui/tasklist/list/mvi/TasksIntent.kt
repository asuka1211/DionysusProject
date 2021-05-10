package com.serma.dionysus.ui.tasklist.list.mvi

import com.serma.dionysus.common.mvi.MviIntent

sealed class TasksIntent : MviIntent {
    data class Loading(val eventId: String, val typeId: String) : TasksIntent()
    data class Reload(val eventId: String, val typeId: String) : TasksIntent()
}