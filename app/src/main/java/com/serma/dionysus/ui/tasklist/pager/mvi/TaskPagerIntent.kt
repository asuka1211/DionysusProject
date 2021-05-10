package com.serma.dionysus.ui.tasklist.pager.mvi

import com.serma.dionysus.common.mvi.MviIntent

sealed class TaskPagerIntent : MviIntent {
    data class Loading(val eventId: String) : TaskPagerIntent()
    data class Reload(val eventId: String) : TaskPagerIntent()
}