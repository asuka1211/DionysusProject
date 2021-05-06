package com.serma.dionysus.domain.interactor

import com.serma.dionysus.ui.graph.GraphItem
import com.serma.dionysus.utils.ColorHelper
import com.serma.dionysus.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GraphInteractor @Inject constructor() {

    private val statusHelper = ColorHelper()
    private val status = listOf(
        GraphItem(
            20f,
            statusHelper.getColor(),
            "Выполнено",
        ),
        GraphItem(
            40f,
            statusHelper.getColor(),
            "В обсуждении",
        ),
        GraphItem(
            20f,
            statusHelper.getColor(),
            "Ожидают",
        ),
        GraphItem(
            30f,
            statusHelper.getColor(),
            "В работе",
        )
    )

    private val budgetHelper = ColorHelper()
    private val budget = listOf(
        GraphItem(
            70f,
            budgetHelper.getColor(),
            "Покупка снюса"
        ),
        GraphItem(
            15f,
            budgetHelper.getColor(),
            "Тодд Говард",
        ),
        GraphItem(
            15f,
            budgetHelper.getColor(),
            "Прочее",
        )
    )

    suspend fun loadStatus(): Flow<Result<List<GraphItem>>> {
        return flow {
            delay(1000L)
            emit(Result.Success(status))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun loadBudget(): Flow<Result<List<GraphItem>>> {
        return flow {
            delay(1000L)
            emit(Result.Success(budget))
        }.flowOn(Dispatchers.IO)
    }
}