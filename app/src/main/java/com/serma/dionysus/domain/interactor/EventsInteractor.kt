package com.serma.dionysus.domain.interactor

import com.serma.dionysus.ui.events.EventData
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventsInteractor @Inject constructor() {

    suspend fun load(pageNumber: Int, pageSize: Int = 50): Flow<Result<MockEvent>> {
        return flow {
            emit(Result.Success(MockEvent(getMock(pageNumber))))
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun getMock(pageNumber: Int): List<EventData> {
        delay(1000L)
        return generateDataFirst()
    }

    private fun generateDataFirst(): List<EventData> {
        return listOf(
            EventData(
                "11",
                "ул. Дегтярева, 2, Волгоград, Волгоградская обл., 400006",
                "12-11-2021",
                "Что? Где? Когда?",
                0.5f
            ),
            EventData(
                "12",
                "ул. Огарева, 7, Волгоград, Волгоградская обл., 400074",
                "21-01-2022",
                "Встреча с друзьями",
                1f
            ),
            EventData(
                "13",
                "ул. Васильсурская, 1, Волгоград, Волгоградская обл., 400012",
                "16-11-2022",
                "Празднование дня рождения",
                0f
            )
        )
    }

}

data class MockEvent(val list: List<EventData>)