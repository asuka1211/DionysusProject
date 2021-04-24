package com.serma.dionysus.ui.login.mvi

import com.serma.dionysus.common.mvi.MviUseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor() : MviUseCase<LoginPartitionState, LoginIntent, LoginEffect>() {
    override fun resolve(intent: LoginIntent): LoginPartitionState {
        return when (intent) {
            is LoginIntent.Login -> loginUseCase(intent)
        }
    }

    private fun loginUseCase(intent: LoginIntent): LoginPartitionState{
        return
    }
}