package com.serma.dionysus.ui.events.mvi

import com.serma.dionysus.common.mvi.MviViewState
import com.serma.dionysus.common.ui.PagingItems
import com.serma.dionysus.ui.events.EventData

data class EventsViewState(
    val error: Throwable? = null,
    val loadingMore: Boolean = false,
    val loading: Boolean = false,
    val events: PagingItems<EventData>? = null,
    val pageNumber: Int = 0,
    val pageSize: Int = 50
) : MviViewState