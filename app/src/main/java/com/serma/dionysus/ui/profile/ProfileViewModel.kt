package com.serma.dionysus.ui.profile

import androidx.lifecycle.viewModelScope
import com.serma.dionysus.common.mvi.MviEffect
import com.serma.dionysus.common.mvi.MviViewModel
import com.serma.dionysus.ui.events.mvi.*
import com.serma.dionysus.ui.profile.mvi.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    reducer: ProfileReducer,
    useCase: ProfileUseCase
) : MviViewModel<MviEffect, ProfileIntent, ProfileViewState, ProfilePartitionState>(
    reducer, useCase
) {

    override fun createInitialState() = ProfileViewState()

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            sendIntent(ProfileIntent.Loading)
        }
    }

    fun reload() {
        viewModelScope.launch {
            sendIntent(ProfileIntent.Reload)
        }
    }

    fun update(data: ProfileData) {
        viewModelScope.launch {
            sendIntent(ProfileIntent.Update(data))
        }
    }
}