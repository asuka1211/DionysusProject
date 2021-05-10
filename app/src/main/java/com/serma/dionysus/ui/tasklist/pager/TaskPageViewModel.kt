package com.serma.dionysus.ui.tasklist.pager

import androidx.lifecycle.viewModelScope
import com.serma.dionysus.common.mvi.MviEffect
import com.serma.dionysus.common.mvi.MviViewModel
import com.serma.dionysus.ui.events.mvi.*
import com.serma.dionysus.ui.profile.mvi.*
import com.serma.dionysus.ui.tasklist.list.mvi.*
import com.serma.dionysus.ui.tasklist.pager.mvi.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskPageViewModel @Inject constructor(
    reducer: TaskPagerReducer,
    useCase: TaskPagerUseCase
) : MviViewModel<MviEffect, TaskPagerIntent, TaskPagerViewState, TaskPagerPartitionState>(
    reducer, useCase
) {

    override fun createInitialState() = TaskPagerViewState()

    fun load(eventId: String) {
        viewModelScope.launch {
            sendIntent(TaskPagerIntent.Loading(eventId))
        }
    }

    fun reload(eventId: String) {
        viewModelScope.launch {
            sendIntent(TaskPagerIntent.Reload(eventId))
        }
    }
}