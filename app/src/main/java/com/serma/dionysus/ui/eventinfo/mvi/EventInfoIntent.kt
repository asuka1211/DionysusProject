package com.serma.dionysus.ui.eventinfo.mvi

import com.serma.dionysus.common.mvi.MviIntent

sealed class EventInfoIntent : MviIntent {
    data class Loading(val eventId: String) : EventInfoIntent()
    data class Reload(val eventId: String) : EventInfoIntent()
}