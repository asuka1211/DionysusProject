package com.serma.dionysus.ui.base

import com.serma.dionysus.common.mvi.MviEffect

sealed class BaseEffect: MviEffect {
    object NavigateProfile: BaseEffect()
    object Logout: BaseEffect()
}