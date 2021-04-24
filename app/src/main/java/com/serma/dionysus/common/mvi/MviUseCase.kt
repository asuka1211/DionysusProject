package com.serma.dionysus.common.mvi

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class MviUseCase<PartitionState : MviPartitionState, Intent : MviIntent, Effect : MviEffect> {

    private val _effectSubscription: MutableSharedFlow<Effect> = MutableSharedFlow()
    val effectSubscription = _effectSubscription.asSharedFlow()

    abstract fun resolve(intent: Intent): PartitionState
}