package com.serma.dionysus.ui.login.mvi

import com.serma.dionysus.common.mvi.MviViewState

data class LoginViewState(
    val error: Throwable? = null,
    val username: String? = null,
    val password: String? = null,
    val state: AuthState = AuthState.NONE
) : MviViewState

enum class AuthState {
    NONE, LOADING, COMPLETE
}