package com.serma.dionysus.ui.base

import com.serma.dionysus.common.mvi.MviViewState

data class BaseViewState(
    val isAuth: Boolean = true
) : MviViewState