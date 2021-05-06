package com.serma.dionysus.ui.profile.mvi

import com.serma.dionysus.common.mvi.MviEffect
import com.serma.dionysus.common.mvi.MviUseCase
import com.serma.dionysus.domain.interactor.EventsInteractor
import com.serma.dionysus.domain.interactor.ProfileInteractor
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val interactor: ProfileInteractor
) : MviUseCase<ProfilePartitionState, ProfileIntent, MviEffect>() {

    override suspend fun resolve(intent: ProfileIntent): Flow<ProfilePartitionState> {
        return when (intent) {
            is ProfileIntent.Loading -> reduceLoading(intent)
            ProfileIntent.Reload -> reduceReload()
            is ProfileIntent.Update -> reduceUpdate(intent)
        }
    }

    private suspend fun reduceLoading(intent: ProfileIntent.Loading): Flow<ProfilePartitionState> {
        return interactor.load().map { result ->
            when (result) {
                is Result.Success -> ProfilePartitionState.LoadingData(result.data)
                is Result.Error -> ProfilePartitionState.Error(result.message)
            }
        }.onStart { emit(ProfilePartitionState.Loading) }
    }

    private suspend fun reduceUpdate(intent: ProfileIntent.Update): Flow<ProfilePartitionState> {
        return interactor.update(intent.data).map { result ->
            when (result) {
                is Result.Success -> ProfilePartitionState.Update
                is Result.Error -> ProfilePartitionState.Error(result.message)
            }
        }
    }

    private suspend fun reduceReload(): Flow<ProfilePartitionState> {
        return interactor.load().map { result ->
            when (result) {
                is Result.Success -> ProfilePartitionState.LoadingData(result.data)
                is Result.Error -> ProfilePartitionState.Error(result.message)
            }
        }.onStart { emit(ProfilePartitionState.Loading) }
    }

}