package com.serma.dionysus.ui.auth.registration

import androidx.lifecycle.viewModelScope
import com.serma.dionysus.common.mvi.MviEffect
import com.serma.dionysus.common.mvi.MviViewModel
import com.serma.dionysus.ui.auth.registration.mvi.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class RegistrationViewModel @Inject constructor(
    reducer: RegistrationReducer,
    useCase: RegistrationUseCase
) : MviViewModel<MviEffect, RegistrationIntent, RegistrationViewState, RegistrationPartitionState>(
    reducer, useCase
) {

    override fun createInitialState() = RegistrationViewState()

    fun registration(email: String, password: String) {
        viewModelScope.launch {
            sendIntent(RegistrationIntent.Registration(email, password))
        }
    }
}