package com.serma.dionysus.ui.login.mvi

import com.serma.dionysus.common.common.mvi.MviViewState

data class LoginViewState(
    val error: Throwable? = null,
    val state: AuthState = AuthState.NONE
) : MviViewState

enum class AuthState {
    NONE, LOADING, COMPLETE
}