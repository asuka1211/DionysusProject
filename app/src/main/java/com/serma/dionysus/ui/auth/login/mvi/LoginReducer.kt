package com.serma.dionysus.ui.auth.login.mvi

import com.serma.dionysus.common.mvi.MviReducer
import javax.inject.Inject

class LoginReducer @Inject constructor() : MviReducer<LoginViewState, LoginPartitionState> {

    override suspend fun reduce(
        lastViewState: LoginViewState,
        partitionState: LoginPartitionState
    ): LoginViewState {
        return when (partitionState) {
            is LoginPartitionState.Error -> reduceError(lastViewState, partitionState)
            LoginPartitionState.Loading -> reduceLoading(lastViewState)
        }
    }

    private fun reduceError(
        lastViewState: LoginViewState,
        state: LoginPartitionState.Error
    ): LoginViewState {
        return lastViewState.copy(error = Throwable(state.message), loading = false)
    }

    private fun reduceLoading(
        lastViewState: LoginViewState
    ): LoginViewState {
        return lastViewState.copy(error = null, loading = true)
    }
}