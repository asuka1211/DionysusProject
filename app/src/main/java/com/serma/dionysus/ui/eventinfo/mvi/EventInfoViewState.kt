package com.serma.dionysus.ui.eventinfo.mvi

import com.serma.dionysus.common.mvi.BaseMviViewState
import com.serma.dionysus.common.mvi.MviViewState
import com.serma.dionysus.common.ui.PagingItems
import com.serma.dionysus.ui.eventinfo.EventInfoData
import com.serma.dionysus.ui.events.EventData
import com.serma.dionysus.ui.profile.ProfileData

data class EventInfoViewState(
    override val error: Throwable? = null,
    override val loading: Boolean = false,
    val data: EventInfoData? = null,
) : BaseMviViewState