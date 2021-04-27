package com.serma.dionysus.common.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class MviUseCase<PartitionState : MviPartitionState, Intent : MviIntent, Effect : MviEffect> {

    protected val _effectSubscription: MutableSharedFlow<Effect> = MutableSharedFlow()
    val effectSubscription = _effectSubscription.asSharedFlow()

    abstract suspend fun resolve(intent: Intent): Flow<PartitionState>
}