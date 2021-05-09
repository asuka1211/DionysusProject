package com.serma.dionysus.ui.auth.login.mvi

import com.serma.dionysus.common.mvi.MviPartitionState

sealed class LoginPartitionState : MviPartitionState {
    object Loading : LoginPartitionState()
    object Success : LoginPartitionState()
    object WrongCredentials : LoginPartitionState()
    data class Error(val message: String) : LoginPartitionState()
}