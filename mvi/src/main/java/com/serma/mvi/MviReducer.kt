package com.serma.mvi

interface MviReducer<ViewState : MviViewState, PartitionState : MviPartitionState> {
    suspend fun reduce(lastViewState: ViewState, partitionState: PartitionState): ViewState
}

