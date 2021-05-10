package com.serma.dionysus.ui.tasklist.list

import androidx.lifecycle.viewModelScope
import com.serma.dionysus.common.mvi.MviEffect
import com.serma.dionysus.common.mvi.MviViewModel
import com.serma.dionysus.ui.events.mvi.*
import com.serma.dionysus.ui.profile.mvi.*
import com.serma.dionysus.ui.tasklist.list.mvi.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    reducer: TasksReducer,
    useCase: TasksUseCase
) : MviViewModel<MviEffect, TasksIntent, TasksViewState, TasksPartitionState>(
    reducer, useCase
) {

    override fun createInitialState() = TasksViewState()

    fun load(eventId: String, typeId: String) {
        viewModelScope.launch {
            sendIntent(TasksIntent.Loading(eventId, typeId))
        }
    }

    fun reload(eventId: String, typeId: String) {
        viewModelScope.launch {
            sendIntent(TasksIntent.Reload(eventId, typeId))
        }
    }
}