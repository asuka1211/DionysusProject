package com.serma.dionysus.domain.interactor

import com.serma.dionysus.ui.graph.BudgetData
import com.serma.dionysus.ui.graph.GraphItem
import com.serma.dionysus.ui.graph.GraphItemBudget
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

    private val allBudget = "40000 ₽"

    private val statusHelper = ColorHelper()
    private val status = listOf(
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
        ),
        GraphItem(
            20f,
            statusHelper.getColor(),
            "Выполнено",
        )
    )

    private val budgetHelper = ColorHelper()
    private val budget = listOf(
        GraphItemBudget(
            45f,
            budgetHelper.getColor(),
            "Покупка еды",
            "18000 ₽"
        ),
        GraphItemBudget(
            15f,
            budgetHelper.getColor(),
            "Оплата за аренду помещения",
            "6000 ₽"
        ),
        GraphItemBudget(
            25f,
            budgetHelper.getColor(),
            "Покупка подарков",
            "10000 ₽"
        ),
        GraphItemBudget(
            15f,
            budgetHelper.getColor(),
            "Прочее",
            "6000 ₽"
        )
    )

    suspend fun loadStatus(): Flow<Result<List<GraphItem>>> {
        return flow {
            delay(1000L)
            emit(Result.Success(status))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun loadBudget(): Flow<Result<BudgetData>> {
        return flow {
            delay(1000L)
            emit(Result.Success(BudgetData(allBudget,budget)))
        }.flowOn(Dispatchers.IO)
    }
}