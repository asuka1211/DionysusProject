package com.serma.dionysus.ui.auth.registration.mvi

import com.serma.dionysus.common.mvi.MviViewState
import com.serma.dionysus.ui.auth.base.AuthState

data class RegistrationViewState(
    val error: Throwable? = null,
    val loading: Boolean = false,
    val authState: AuthState = AuthState.NONE
) : MviViewState