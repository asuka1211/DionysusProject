package com.serma.dionysus.ui.login.mvi

import com.serma.dionysus.common.common.mvi.MviIntent

sealed class LoginIntent : MviIntent {
  data class Login(val email: String, val password: String): LoginIntent()
}