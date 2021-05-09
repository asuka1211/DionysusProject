package com.serma.dionysus.ui.auth.registration.mvi

import com.serma.dionysus.auth.manager.SessionManager
import com.serma.dionysus.common.mvi.MviEffect
import com.serma.dionysus.common.mvi.MviUseCase
import com.serma.dionysus.domain.interactor.AuthInteractor
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val interactor: AuthInteractor,
    private val sessionManager: SessionManager
) : MviUseCase<RegistrationPartitionState, RegistrationIntent, MviEffect>() {

    override suspend fun resolve(intent: RegistrationIntent): Flow<RegistrationPartitionState> {
        return when (intent) {
            is RegistrationIntent.Registration -> registrationUseCase(intent)
        }
    }

    private suspend fun registrationUseCase(intent: RegistrationIntent.Registration): Flow<RegistrationPartitionState> {
        return interactor.registration(intent.email, intent.password).map { result ->
            when (result) {
                is Result.Success -> {
                    if (result.data.wrongCredentials) {
                        RegistrationPartitionState.WrongCredentials
                    } else {
                        sessionManager.saveTokenData(
                            result.data.token,
                            result.data.refreshToken,
                            result.data.expiresTime
                        )
                        RegistrationPartitionState.Success
                    }
                }
                is Result.Error -> {
                    RegistrationPartitionState.Error(result.message)
                }
            }
        }.onStart {
            emit(RegistrationPartitionState.Loading)
        }
    }
}