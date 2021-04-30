package com.serma.dionysus.ui.eventinfo.mvi

import com.serma.dionysus.common.mvi.MviPartitionState
import com.serma.dionysus.ui.eventinfo.EventInfoData
import com.serma.dionysus.ui.profile.ProfileData
import com.serma.dionysus.ui.profile.mvi.ProfilePartitionState

sealed class EventInfoPartitionState : MviPartitionState {

    object Loading : EventInfoPartitionState()
    data class LoadingData(val data: EventInfoData) : EventInfoPartitionState()

    data class Error(val message: String) : EventInfoPartitionState()
}