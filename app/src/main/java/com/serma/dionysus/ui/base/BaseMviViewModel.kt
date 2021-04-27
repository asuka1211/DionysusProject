package com.serma.dionysus.ui.base

import com.serma.dionysus.common.mvi.MviViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
abstract class BaseMviViewModel constructor(
    reducer: BaseReducer,
    useCase: BaseUseCase
) : MviViewModel<BaseEffect, BaseIntent, BaseViewState, BasePartitionState>(
    reducer, useCase
)