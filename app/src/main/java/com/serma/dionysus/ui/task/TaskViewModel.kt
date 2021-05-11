package com.serma.dionysus.ui.task

import androidx.lifecycle.viewModelScope
import com.serma.dionysus.common.mvi.MviEffect
import com.serma.dionysus.common.mvi.MviViewModel
import com.serma.dionysus.ui.task.mvi.TaskIntent
import com.serma.dionysus.ui.task.mvi.TaskPartitionState
import com.serma.dionysus.ui.task.mvi.TaskReducer
import com.serma.dionysus.ui.task.mvi.TaskUseCase
import com.serma.dionysus.ui.task.mvi.TaskViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TaskViewModel @Inject constructor(
    reducer: TaskReducer,
    useCase: TaskUseCase
) : MviViewModel<MviEffect, TaskIntent, TaskViewState, TaskPartitionState>(
    reducer, useCase
) {

    override fun createInitialState() = TaskViewState()

    fun load(eventId: String, taskId: String) {
        viewModelScope.launch {
            sendIntent(TaskIntent.Loading(eventId, taskId))
        }
    }

    fun reload(eventId: String, taskId: String) {
        viewModelScope.launch {
            sendIntent(TaskIntent.Reload(eventId, taskId))
        }
    }

}