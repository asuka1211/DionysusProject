package com.serma.dionysus.ui.login.mvi

import com.serma.dionysus.common.common.mvi.MviReducer

class LoginReducer: MviReducer<LoginViewState, LoginPartitionState> {
    override suspend fun reduce(
        lastViewState: LoginViewState,
        partitionState: LoginPartitionState
    ): LoginViewState {
        TODO("Not yet implemented")
    }
}