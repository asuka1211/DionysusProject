package com.serma.dionysus.ui.auth.registration.mvi

import com.serma.dionysus.common.mvi.MviReducer
import com.serma.dionysus.ui.auth.base.AuthState
import javax.inject.Inject

class RegistrationReducer @Inject constructor() : MviReducer<RegistrationViewState, RegistrationPartitionState> {

    override suspend fun reduce(
        lastViewState: RegistrationViewState,
        partitionState: RegistrationPartitionState
    ): RegistrationViewState {
        return when (partitionState) {
            is RegistrationPartitionState.Error -> reduceError(lastViewState, partitionState)
            RegistrationPartitionState.Loading -> reduceLoading(lastViewState)
            RegistrationPartitionState.Success -> reduceSuccess(lastViewState)
            RegistrationPartitionState.WrongCredentials -> reduceWrongCredentials(lastViewState)
        }
    }

    private fun reduceError(
        lastViewState: RegistrationViewState,
        state: RegistrationPartitionState.Error
    ): RegistrationViewState {
        return lastViewState.copy(
            error = Throwable(state.message),
            loading = false,
            authState = AuthState.NONE
        )
    }

    private fun reduceSuccess(
        lastViewState: RegistrationViewState
    ): RegistrationViewState {
        return lastViewState.copy(error = null, loading = false, authState = AuthState.SUCCESS)
    }

    private fun reduceWrongCredentials(
        lastViewState: RegistrationViewState
    ): RegistrationViewState {
        return lastViewState.copy(
            error = null,
            loading = false,
            authState = AuthState.WRONG_CREDENTIALS
        )
    }

    private fun reduceLoading(
        lastViewState: RegistrationViewState
    ): RegistrationViewState {
        return lastViewState.copy(error = null, loading = true, authState = AuthState.NONE)
    }
}