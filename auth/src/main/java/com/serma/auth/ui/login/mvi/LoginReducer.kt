package com.serma.auth.ui.login.mvi

import com.serma.mvi.MviReducer

class LoginReducer: MviReducer<LoginViewState, LoginPartitionState> {
    override suspend fun reduce(
        lastViewState: LoginViewState,
        partitionState: LoginPartitionState
    ): LoginViewState {
        TODO("Not yet implemented")
    }
}