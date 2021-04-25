package com.serma.dionysus.ui.login

import androidx.lifecycle.viewModelScope
import com.serma.dionysus.common.mvi.MviViewModel
import com.serma.dionysus.ui.login.mvi.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class LoginViewModel @Inject constructor(
    reducer: LoginReducer,
    useCase: LoginUseCase
) : MviViewModel<LoginEffect, LoginIntent, LoginViewState, LoginPartitionState>(
    reducer, useCase
) {

    override fun createInitialState() = LoginViewState()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            sendIntent(LoginIntent.Login(username, password))
        }
    }
}