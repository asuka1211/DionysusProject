package com.serma.dionysus.ui.login.mvi

import com.serma.dionysus.auth.manager.SessionManager
import com.serma.dionysus.common.mvi.MviUseCase
import com.serma.dionysus.domain.interactor.LoginInteractor
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val interactor: LoginInteractor,
    private val sessionManager: SessionManager
) : MviUseCase<LoginPartitionState, LoginIntent, LoginEffect>() {

    override suspend fun resolve(intent: LoginIntent): Flow<LoginPartitionState> {
        return when (intent) {
            is LoginIntent.Login -> loginUseCase(intent)
        }
    }

    private suspend fun loginUseCase(intent: LoginIntent.Login): Flow<LoginPartitionState> {
        return interactor.login(intent.email, intent.password).map { result ->
//            when (result) {
//                is Result.Success -> {
//                    if (result.data.wrongCredentials) {
//                        LoginPartitionState.WrongCredentials
//                    } else {
//                        sessionManager.saveTokenData(
//                            result.data.token,
//                            result.data.refreshToken,
//                            result.data.expiresTime
//                        )
//                        LoginPartitionState.Success
//                    }
//                }
//                is Result.Error -> {
                    LoginPartitionState.Loading as LoginPartitionState
                //}

        }.onStart {
           emit( LoginPartitionState.Loading)
        }
    }
}