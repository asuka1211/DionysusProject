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

    private val testData1 = PersonData(
        "1",
        "Максим Яковлев",
        "https://s0.rbk.ru/v6_top_pics/media/img/5/46/756038770746465.jpg"
    )
    private val testData2 = PersonData(
        "2",
        "Олег Тинькофф",
        "https://interesnyefakty.org/wp-content/uploads/oleg-tinkov.jpg"
    )
    val listTestData1 = listOf(testData1, testData2)
    val listTestData2 = listOf(testData1)
    val listTestData3 = listOf(testData2)

    val tasks = listOf(AddingTaskData(
        "i1",
        "Покупка еды",
        "10-12-2021",
        "Сходить в магазин и купить еды",
        testData1,
        listTestData1,
        "#Еда",
        "Срочная задача"
    ),AddingTaskData(
        "d1",
        "Купить подарки",
        "10-12-2021",
        "Купить подарки на др",
        testData2,
        listTestData2,
        "",
        "Срочная задача"
    ),AddingTaskData(
        "i2",
        "Оплата за аренду помещения",
        "10-12-2021",
        "Заплатить арендадателю",
        testData2,
        listTestData3,
        "#Аренда",
        "Задача средней важности"
    ),AddingTaskData(
        "r1",
        "Прочее",
        "10-12-2021",
        "Убрать дом после дня рождения",
        testData1,
        listTestData1,
        "",
        "Несрочная задача"
    ))

    suspend fun load(taskId: String): Flow<Result<AddingTaskData>> {
        return flow {
            delay(1000L)
            emit(Result.Success(tasks.first{it.id == taskId}))
        }.flowOn(Dispatchers.IO)
    }

}