package com.serma.dionysus.common.mvi

interface BaseMviViewState : MviViewState {
    val error: Throwable?
    val loading: Boolean
}