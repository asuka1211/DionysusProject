package com.serma.dionysus.ui.events.mvi

import com.serma.dionysus.common.mvi.BaseMviViewState
import com.serma.dionysus.common.mvi.MviViewState
import com.serma.dionysus.common.ui.PagingItems
import com.serma.dionysus.ui.events.EventData

data class EventsViewState(
    val loadingMore: Boolean = false,
    val canLoading: Boolean = false,
    val events: PagingItems<EventData>? = null,
    val searchText: String = "",
    val pageNumber: Int = 0,
    val pageSize: Int = 50,
    override val error: Throwable? = null,
    override val loading: Boolean = false
) : BaseMviViewState