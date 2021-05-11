package com.serma.dionysus.domain.interactor

import com.serma.dionysus.common.ui.PersonData
import com.serma.dionysus.ui.task.AddingTaskData
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskInteractor @Inject constructor() {

    val testData = PersonData(
        "1",
        "Максим Яковлев",
        "https://s0.rbk.ru/v6_top_pics/media/img/5/46/756038770746465.jpg"
    )
    val listTestData = listOf(testData, testData)

    val tagList = listOf("первый", "второй", "третий")

    val data = AddingTaskData(
        1,
        "Создание приложения",
        "Завтра",
        "Обсуждение необходимых технологий",
        testData,
        listTestData,
        "первый",
        "Срочная задача"
    )


    suspend fun load(eventId: String): Flow<Result<AddingTaskData>> {
        return flow {
            delay(1000L)
            emit(Result.Success(data))
        }.flowOn(Dispatchers.IO)
    }

}