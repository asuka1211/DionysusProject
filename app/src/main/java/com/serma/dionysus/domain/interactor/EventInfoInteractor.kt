package com.serma.dionysus.domain.interactor

import com.serma.dionysus.common.ui.PersonData
import com.serma.dionysus.ui.eventinfo.EventInfoData
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventInfoInteractor @Inject constructor() {

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
    private val listTestData = listOf(testData1, testData2)

    private val data = EventInfoData(
        "Празднование дня рождения",
        "16-11-2022",
        "Празднование дня рождения Александра",
        listTestData,
        "ул. Васильсурская, 1, Волгоград, Волгоградская обл., 400012",
        "40000 ₽"
    )

    suspend fun load(eventId: String): Flow<Result<EventInfoData>> {
        return flow {
            delay(1000L)
            emit(Result.Success(data))
        }.flowOn(Dispatchers.IO)
    }

}