package com.serma.dionysus.ui.auth.login.mvi

import com.serma.dionysus.common.mvi.MviReducer
import com.serma.dionysus.ui.auth.base.AuthState
import javax.inject.Inject

class LoginReducer @Inject constructor() : MviReducer<LoginViewState, LoginPartitionState> {

    override suspend fun reduce(
        lastViewState: LoginViewState,
        partitionState: LoginPartitionState
    ): LoginViewState {
        return when (partitionState) {
            is LoginPartitionState.Error -> reduceError(lastViewState, partitionState)
            LoginPartitionState.Loading -> reduceLoading(lastViewState)
            LoginPartitionState.Success -> reduceSuccess(lastViewState)
            LoginPartitionState.WrongCredentials -> reduceWrongCredentials(lastViewState)
        }
    }

    private fun reduceError(
        lastViewState: LoginViewState,
        state: LoginPartitionState.Error
    ): LoginViewState {
        return lastViewState.copy(
            error = Throwable(state.message),
            loading = false,
            authState = AuthState.NONE
        )
    }

    private fun reduceSuccess(
        lastViewState: LoginViewState
    ): LoginViewState {
        return lastViewState.copy(error = null, loading = false, authState = AuthState.SUCCESS)
    }

    private fun reduceWrongCredentials(
        lastViewState: LoginViewState
    ): LoginViewState {
        return lastViewState.copy(
            error = null,
            loading = false,
            authState = AuthState.WRONG_CREDENTIALS
        )
    }

    private fun reduceLoading(
        lastViewState: LoginViewState
    ): LoginViewState {
        return lastViewState.copy(error = null, loading = true, authState = AuthState.NONE)
    }
}