package com.serma.auth.ui.login

import com.serma.auth.ui.login.mvi.*
import com.serma.mvi.MviViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class LoginViewModel constructor(
    reducer: LoginReducer,
    useCase: LoginUseCase
) : MviViewModel<LoginEffect, LoginIntent, LoginViewState, LoginPartitionState>(
    reducer, useCase
) {
    override fun createInitialState(): LoginViewState {
        TODO("Not yet implemented")
    }
}