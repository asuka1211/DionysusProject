package com.serma.dionysus.ui.auth.login.mvi

import com.serma.dionysus.common.mvi.MviViewState
import com.serma.dionysus.ui.auth.base.AuthState

data class LoginViewState(
    val error: Throwable? = null,
    val loading: Boolean = false,
    val authState: AuthState = AuthState.NONE
) : MviViewState