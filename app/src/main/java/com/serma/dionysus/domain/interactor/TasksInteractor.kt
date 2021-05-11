package com.serma.dionysus.domain.interactor

import androidx.compose.ui.graphics.Color
import com.serma.dionysus.ui.tasklist.list.DirectionsSwipeTask
import com.serma.dionysus.ui.tasklist.list.TaskListData
import com.serma.dionysus.ui.tasklist.list.TaskPageInfo
import com.serma.dionysus.ui.tasklist.list.TasksTypeData
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksInteractor @Inject constructor() {

    private val firstGroup = TaskPageInfo(
        leftColor = null,
        rightColor = Color.Blue,
        directions = DirectionsSwipeTask.RIGHT,
        leftId = null,
        rightId = "2"
    )
    private val secondGroup =  TaskPageInfo(
        leftColor = Color.Yellow,
        rightColor = Color.Green,
        directions = DirectionsSwipeTask.LEFT_AND_RIGHT,
        leftId = "1",
        rightId = "3"
    )
    private val thirdGroup = TaskPageInfo(
        leftColor = Color.Blue,
        rightColor = null,
        directions = DirectionsSwipeTask.LEFT,
        leftId = "2",
        rightId = null
    )

    private var discussTasks = mutableListOf(
        TaskListData(
            "d1",
            "",
            "Купить подарки",
            "10-12-2021",
            null,
            firstGroup
        )
    )

    private var inProgressTasks = mutableListOf(
        TaskListData(
            "i1",
            "#Еда",
            "Покупка еды",
            "10-12-2021",
            null,
            secondGroup
        ),TaskListData(
            "i2",
            "#Аренда",
            "Оплата за аренду помещения",
            "10-12-2021",
            "Прочее",
            secondGroup
        )
    )

    private var readyTasks = mutableListOf(
        TaskListData(
            "r1",
            "",
            "Прочее",
            "10-12-2021",
            null,
            thirdGroup
        )
    )

    private var discussData = mutableListOf(
        TasksTypeData(
            "1", "В обсуждении", Color.Yellow, discussTasks
        ),
        TasksTypeData(
            "2", "В работе", Color.Blue, inProgressTasks
        ),
        TasksTypeData(
            "3", "Выполненные", Color.Green, readyTasks
        )
    )

    suspend fun load(eventId: String, pageId: String): Flow<Result<TasksTypeData>> {
        return flow {
            delay(100L)
            emit(Result.Success(discussData.first { it.typeId == pageId }))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun move(
        taskId: String,
        nextId: String,
        pageId: String
    ): Flow<Result<TasksTypeData>> {
        return flow {
            val list = discussData.first { it.typeId == pageId }.tasks.toMutableList()
            val task = list.find { it.id == taskId }.also { list.remove(it) }
            task?.let {
                discussData[discussData.indexOfFirst { it.typeId == pageId }] =
                    discussData[discussData.indexOfFirst { it.typeId == pageId }].copy(tasks = list)
                val result = discussData.first { it.typeId == nextId }.tasks.toMutableList()
                result.add(mapTask(task, nextId))
                discussData[discussData.indexOfFirst { it.typeId == nextId }] =
                    discussData[discussData.indexOfFirst { it.typeId == nextId }].copy(tasks = result)
                emit(Result.Success(discussData[discussData.indexOfFirst { it.typeId == pageId }]))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun loadTask(eventId: String, taskId: String): Flow<Result<TasksTypeData>> {
        return flow {
            delay(100L)
            emit(Result.Success(discussData.first { it.typeId == taskId }))
        }.flowOn(Dispatchers.IO)
    }

    private fun mapTask(task: TaskListData, nextId: String): TaskListData{
        return task.copy(taskPageInfo = when(nextId){
            "1" -> firstGroup
            "2" -> secondGroup
            "3" -> thirdGroup
            else -> firstGroup
        })
    }
}