package com.serma.dionysus.ui.base

import com.serma.dionysus.common.mvi.MviIntent

sealed class BaseIntent : MviIntent {
  object Logout: BaseIntent()
}