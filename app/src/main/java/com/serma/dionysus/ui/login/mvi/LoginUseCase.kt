package com.serma.dionysus.ui.login.mvi

import com.serma.dionysus.common.mvi.MviUseCase

class LoginUseCase : MviUseCase<LoginPartitionState, LoginIntent, LoginEffect>() {
    override suspend fun resolve(intent: LoginIntent): LoginPartitionState {
        return when (intent) {
            is LoginIntent.Login -> TODO()
        }
    }

//    private fun loginUseCase(intent: LoginIntent): LoginPartitionState{
//        return
//    }
}