package com.serma.dionysus.ui.splash

import androidx.lifecycle.viewModelScope
import com.serma.dionysus.auth.manager.SessionManager
import com.serma.dionysus.common.mvi.simple.SimpleMviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val sessionManager: SessionManager) :
    SimpleMviViewModel<SplashViewStateState>() {

    init {
        checkSession()
    }

    override fun createInitialState(): SplashViewStateState = SplashViewStateState()

    private fun checkSession() {
        viewModelScope.launch {
            val authState = if (true) { //sessionManager.isAuth() вернуть
                AuthStateSplash.AUTH
            } else {
                AuthStateSplash.NOT_AUTH
            }
            delay(500L) //TODO: Добавить анимацию
            _uiState.emit(currentState.copy(isAuth = authState))
        }
    }
}