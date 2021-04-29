package com.serma.dionysus.ui.profile.mvi

import com.serma.dionysus.common.mvi.MviPartitionState
import com.serma.dionysus.ui.events.EventData
import com.serma.dionysus.ui.profile.ProfileData

sealed class ProfilePartitionState : MviPartitionState {

    object Loading : ProfilePartitionState()
    data class LoadingData(val data: ProfileData) : ProfilePartitionState()

    object Update : ProfilePartitionState()

    data class Error(val message: String) : ProfilePartitionState()
}