package com.serma.dionysus.ui.base

import com.serma.dionysus.common.mvi.MviReducer
import javax.inject.Inject

class BaseReducer @Inject constructor() : MviReducer<BaseViewState, BasePartitionState> {

    override suspend fun reduce(
        lastViewState: BaseViewState,
        partitionState: BasePartitionState
    ): BaseViewState {
        return when (partitionState) {
            BasePartitionState.Logout -> reduceLogout(lastViewState)
        }
    }

    private fun reduceLogout(
        lastViewState: BaseViewState
    ): BaseViewState {
        return lastViewState.copy(isAuth = false)
    }
}