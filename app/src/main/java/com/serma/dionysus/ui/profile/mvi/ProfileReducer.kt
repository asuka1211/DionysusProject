package com.serma.dionysus.ui.profile.mvi

import com.serma.dionysus.common.mvi.MviReducer
import com.serma.dionysus.common.ui.PagingItems
import com.serma.dionysus.ui.events.EventData
import com.serma.dionysus.ui.events.mvi.EventsViewState
import javax.inject.Inject

class ProfileReducer @Inject constructor() : MviReducer<ProfileViewState, ProfilePartitionState> {

    override suspend fun reduce(
        lastViewState: ProfileViewState,
        partitionState: ProfilePartitionState
    ): ProfileViewState {
        return when (partitionState) {
            is ProfilePartitionState.Error -> reduceError(lastViewState, partitionState)
            ProfilePartitionState.Loading -> reduceLoading(lastViewState)
            is ProfilePartitionState.LoadingData -> reduceLoadingData(lastViewState, partitionState)
            ProfilePartitionState.Update -> reduceUpdate(lastViewState)
        }
    }

    private fun reduceError(
        lastViewState: ProfileViewState,
        state: ProfilePartitionState.Error
    ): ProfileViewState {
        return lastViewState.copy(error = Throwable(state.message), loading = false)
    }

    private fun reduceLoading(lastViewState: ProfileViewState,): ProfileViewState {
        return lastViewState.copy(error = null, loading = true)
    }

    private fun reduceLoadingData(
        lastViewState: ProfileViewState,
        state: ProfilePartitionState.LoadingData
    ): ProfileViewState {
        return lastViewState.copy(
            error = null,
            loading = false,
            data = state.data,
        )
    }

    private fun reduceUpdate(
        lastViewState: ProfileViewState
    ): ProfileViewState {
        return lastViewState
    }
}