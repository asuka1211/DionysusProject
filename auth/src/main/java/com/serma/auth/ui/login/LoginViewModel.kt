package com.serma.auth.ui.login

import com.serma.auth.ui.login.mvi.LoginEffect
import com.serma.auth.ui.login.mvi.LoginIntent
import com.serma.auth.ui.login.mvi.LoginPartitionState
import com.serma.auth.ui.login.mvi.LoginViewState
import com.serma.mvi.MviViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class LoginViewModel: MviViewModel<LoginEffect,LoginIntent,LoginViewState,LoginPartitionState>() {
    override fun createInitialState(): LoginViewState {
        TODO("Not yet implemented")
    }
}