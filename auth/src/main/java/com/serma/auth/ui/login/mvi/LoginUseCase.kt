package com.serma.auth.ui.login.mvi

import com.serma.auth.ui.login.mvi.LoginEffect
import com.serma.auth.ui.login.mvi.LoginIntent
import com.serma.auth.ui.login.mvi.LoginPartitionState
import com.serma.mvi.MviUseCase

class LoginUseCase : MviUseCase<LoginPartitionState, LoginIntent, LoginEffect>() {
    override suspend fun resolve(intent: LoginIntent): LoginPartitionState {
        TODO("Not yet implemented")
    }
}