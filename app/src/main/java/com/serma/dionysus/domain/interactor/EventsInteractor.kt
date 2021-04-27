package com.serma.dionysus.domain.interactor

import com.serma.dionysus.ui.events.EventData
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EventsInteractor @Inject constructor() {

    suspend fun load(pageNumber: Int, pageSize: Int = 50): Flow<Result<MockEvent>> {
        return flow {
            emit(Result.Success(MockEvent(getMock(pageNumber))))
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getMock(pageNumber: Int): List<EventData> {
        delay(1000L)
        return if (pageNumber == 0) {
            generateDataFirst()
        } else {
            generateDataSecond()
        }
    }

    private fun generateDataFirst(): List<EventData> {
        return listOf(
            EventData(
                "Asd",
                "Пивная",
                "12-11-2021",
                "Насвай пати",
                0.5f
            ),
            EventData(
                "Asd",
                "Снюсовая",
                "21-01-2022",
                "День выборов",
                1f
            ),
            EventData(
                "Asd",
                "Васильсурская 1",
                "16-11-2022",
                "День рождение",
                0f
            ),
            EventData(
                "Asd",
                "Васильсурasdasdsadsadsa sad asdsa dsad asd asd sad sad asd aская 1",
                "16-11-2022",
                "Денsadasdsad asda sdasd asd asda sdasdasdasdsadaь рdsadasdоaasждение",
                0.3f
            )
        )
    }

    private fun generateDataSecond(): List<EventData> {
        return listOf(
            EventData(
                "Asd",
                "1",
                "12-11-2021",
                "Наadsaсвай пати",
                0.5f
            ),
            EventData(
                "Asd",
                "2",
                "21-01-2022",
                "Деньas dsad  выборов",
                1f
            ),
            EventData(
                "Asd",
                "3 1",
                "16-11-2022",
                "День рd ada sождение",
                0f
            ),
            EventData(
                "Asd",
                "Васильсурasdasdsadsadsa sad asdsa dsad asd asd sad sad asd aская 1",
                "16-11-2022",
                "Денsadasdsad assad asd asdda sdasd asd asda sdasdasdasdsadaь рdsadasdоaasждение",
                0.3f
            )
        )
    }
}

data class MockEvent(val list: List<EventData>)