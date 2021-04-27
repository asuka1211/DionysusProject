package com.serma.dionysus.ui.events.mvi

import com.serma.dionysus.common.mvi.MviIntent

sealed class EventsIntent : MviIntent {
    data class Loading(val pageSize: Int = 50) : EventsIntent()
    data class LoadingMore(val pageNumber: Int, val pageSize: Int = 50) : EventsIntent()
    object Reload : EventsIntent()
}