package com.serma.dionysus.ui.splash

import com.serma.dionysus.common.mvi.MviViewState

data class SplashViewStateState(val isAuth: AuthStateSplash = AuthStateSplash.WAIT) : MviViewState

enum class AuthStateSplash {
    WAIT, AUTH, NOT_AUTH
}