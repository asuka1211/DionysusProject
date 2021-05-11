package com.serma.dionysus.ui.task.mvi

import com.serma.dionysus.common.mvi.BaseMviViewState
import com.serma.dionysus.ui.task.AddingTaskData

data class TaskViewState(
    override val error: Throwable? = null,
    override val loading: Boolean = false,
    val data: AddingTaskData? = null,
) : BaseMviViewState