package com.serma.dionysus.ui.login

import com.serma.dionysus.common.common.mvi.MviViewModel
import com.serma.dionysus.ui.login.mvi.*
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