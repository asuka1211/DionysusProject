package com.serma.dionysus.ui.login.mvi

import com.serma.dionysus.common.mvi.MviPartitionState

sealed class LoginPartitionState : MviPartitionState {
    object Loading : LoginPartitionState()
    data class Error(val message: String) : LoginPartitionState()
}