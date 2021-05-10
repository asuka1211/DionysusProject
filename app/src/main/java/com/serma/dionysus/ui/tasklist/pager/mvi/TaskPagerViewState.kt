package com.serma.dionysus.ui.tasklist.pager.mvi

import com.serma.dionysus.common.mvi.BaseMviViewState
import com.serma.dionysus.ui.profile.ProfileData
import com.serma.dionysus.ui.tasklist.pager.TaskPagerData

data class TaskPagerViewState(
    override val error: Throwable? = null,
    override val loading: Boolean = false,
    val data: TaskPagerData? = null,
) : BaseMviViewState