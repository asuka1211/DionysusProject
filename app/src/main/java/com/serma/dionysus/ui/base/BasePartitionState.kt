package com.serma.dionysus.ui.base

import com.serma.dionysus.common.mvi.MviPartitionState

sealed class BasePartitionState : MviPartitionState {
    object Logout : BasePartitionState()
}