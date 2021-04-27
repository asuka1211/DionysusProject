package com.serma.dionysus.ui.base

import com.serma.dionysus.auth.manager.SessionManager
import com.serma.dionysus.common.mvi.MviUseCase
import com.serma.dionysus.domain.interactor.LoginInteractor
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class BaseUseCase @Inject constructor(
    private val interactor: LoginInteractor,
    private val sessionManager: SessionManager
) : MviUseCase<BasePartitionState, BaseIntent, BaseEffect>() {

    override suspend fun resolve(intent: BaseIntent): Flow<BasePartitionState> {
        return when (intent) {
            BaseIntent.Logout -> resolveLogout()
        }
    }

    private suspend fun resolveLogout(): Flow<BasePartitionState> {
        return interactor.logout(sessionManager.getToken()!!).map { BasePartitionState.Logout }
            .onCompletion { sessionManager.logout() }
    }

}