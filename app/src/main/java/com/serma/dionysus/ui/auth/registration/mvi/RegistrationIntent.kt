package com.serma.dionysus.ui.auth.registration.mvi

import com.serma.dionysus.common.mvi.MviIntent

sealed class RegistrationIntent : MviIntent {
    data class Registration(
        val email: String,
        val password: String
    ) : RegistrationIntent()
}