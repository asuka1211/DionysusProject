package com.serma.dionysus.ui.auth.registration.mvi

import com.serma.dionysus.common.mvi.MviPartitionState

sealed class RegistrationPartitionState : MviPartitionState {
    object Loading : RegistrationPartitionState()
    object Success : RegistrationPartitionState()
    object WrongCredentials : RegistrationPartitionState()
    data class Error(val message: String) : RegistrationPartitionState()
}