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

    private val testData = PersonData(
        "Максим Яковлев",
        "https://s0.rbk.ru/v6_top_pics/media/img/5/46/756038770746465.jpg"
    )
    private val listTestData = listOf(testData, testData, testData, testData)

    private val data = EventInfoData(
        "Сдерживание грузина",
        "Завтра",
        "Сдерживание мощного, не молодого грузина посредством применения специально оборудованных водометов",
        listTestData,
        "Политех",
        "300$"
    )

    suspend fun load(eventId: String): Flow<Result<EventInfoData>> {
        return flow {
            delay(1000L)
            emit(Result.Success(data))
        }.flowOn(Dispatchers.IO)
    }

}