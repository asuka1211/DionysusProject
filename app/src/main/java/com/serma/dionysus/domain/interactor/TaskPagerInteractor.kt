package com.serma.dionysus.domain.interactor

import com.serma.dionysus.ui.profile.ProfileData
import com.serma.dionysus.ui.tasklist.pager.TaskPagerData
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TaskPagerInteractor @Inject constructor() {
    private var localMockData = TaskPagerData(
        listOf("1","2","3")
    )

    suspend fun load(eventId: String): Flow<Result<TaskPagerData>> {
        return flow {
            delay(500L)
            emit(Result.Success(localMockData))
        }.flowOn(Dispatchers.IO)
    }

}