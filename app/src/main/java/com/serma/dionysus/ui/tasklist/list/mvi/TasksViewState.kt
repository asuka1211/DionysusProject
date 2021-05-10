package com.serma.dionysus.ui.tasklist.list.mvi

import com.serma.dionysus.common.mvi.BaseMviViewState
import com.serma.dionysus.ui.tasklist.list.TasksTypeData

data class TasksViewState(
    override val error: Throwable? = null,
    override val loading: Boolean = false,
    val data: Map<String, TasksTypeData> = hashMapOf(),
) : BaseMviViewState