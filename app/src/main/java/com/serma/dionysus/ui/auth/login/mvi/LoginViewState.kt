package com.serma.dionysus.ui.auth.login.mvi

import com.serma.dionysus.common.mvi.MviViewState

data class LoginViewState(
    val error: Throwable? = null,
    val loading: Boolean = false,
) : MviViewState

enum class AuthState {
    NONE, LOADING, SUCCESS, WRONG_CREDENTIALS
}