package com.serma.dionysus.ui.events.mvi

import com.serma.dionysus.common.mvi.MviIntent

sealed class EventsIntent : MviIntent {
    data class Loading(val name: String = "", val pageSize: Int = 50) : EventsIntent()
    data class LoadingMore(val name: String = "", val pageNumber: Int, val pageSize: Int = 50) :
        EventsIntent()

    data class Reload(val name: String = "") : EventsIntent()
}