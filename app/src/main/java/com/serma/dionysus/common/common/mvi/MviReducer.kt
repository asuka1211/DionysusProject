package com.serma.dionysus.common.common.mvi

interface MviReducer<ViewState : MviViewState, PartitionState : MviPartitionState> {
    suspend fun reduce(lastViewState: ViewState, partitionState: PartitionState): ViewState
}

