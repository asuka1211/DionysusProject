package com.serma.dionysus.domain.interactor

import androidx.compose.ui.graphics.Color
import com.serma.dionysus.ui.tasklist.list.TaskListData
import com.serma.dionysus.ui.tasklist.list.TasksTypeData
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TasksInteractor @Inject constructor() {

    private var discussTasks = listOf(
        TaskListData(
            "d1",
            "",
            "Купить подарки",
            "10-12-2021",
            null
        )
    )

    private var readyTasks = listOf(
        TaskListData(
            "d1",
            "",
            "Купить подарки",
            "10-12-2021",
            null
        )
    )


    private var discussData = listOf(
        TasksTypeData(
            "1", "В обсуждении", Color.Yellow, discussTasks
        ),
        TasksTypeData(
            "2", "В работе", Color.Blue, discussTasks
        ),
        TasksTypeData(
            "3", "Выполненные", Color.Green, discussTasks
        )
    )

    suspend fun load(eventId: String, pageId: String): Flow<Result<TasksTypeData>> {
        return flow {
            delay(100L)
            emit(Result.Success(discussData.first { it.typeId == pageId }))
        }.flowOn(Dispatchers.IO)
    }

}