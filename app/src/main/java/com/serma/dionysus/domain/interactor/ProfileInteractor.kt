package com.serma.dionysus.domain.interactor

import com.serma.dionysus.ui.profile.ProfileData
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfileInteractor @Inject constructor() {

    private var localMockData = ProfileData(
        "https://flamp.ru/wd72983",
        "Тодд Говард",
        "12-11-1999",
        "Бесезда",
    )

    suspend fun load(): Flow<Result<ProfileData>> {
        return flow {
            delay(1000L)
            emit(Result.Success(localMockData))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun update(data: ProfileData): Flow<Result<ProfileData>> {
        return flow {
            localMockData = data
            emit(Result.Success(localMockData))
        }.flowOn(Dispatchers.IO)
    }
}