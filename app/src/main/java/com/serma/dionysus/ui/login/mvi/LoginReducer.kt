package com.serma.dionysus.ui.login.mvi

import com.serma.dionysus.common.mvi.MviReducer
import javax.inject.Inject

class LoginReducer @Inject constructor(): MviReducer<LoginViewState, LoginPartitionState> {
    override suspend fun reduce(
        lastViewState: LoginViewState,
        partitionState: LoginPartitionState
    ): LoginViewState {
        TODO("Not yet implemented")
    }
}