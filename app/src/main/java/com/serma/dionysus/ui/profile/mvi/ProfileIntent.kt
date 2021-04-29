package com.serma.dionysus.ui.profile.mvi

import com.serma.dionysus.common.mvi.MviIntent
import com.serma.dionysus.ui.profile.ProfileData

sealed class ProfileIntent : MviIntent {
    object Loading : ProfileIntent()
    data class Update(val data: ProfileData) : ProfileIntent()
    object Reload : ProfileIntent()
}