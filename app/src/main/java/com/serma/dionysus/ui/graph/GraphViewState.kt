package com.serma.dionysus.ui.graph

import com.serma.dionysus.common.mvi.BaseMviViewState

data class GraphViewState(
    val dataBudget: BudgetData? = null,
    val dataStatus: List<GraphItem>? = null,
    override val error: Throwable? = null,
    override val loading: Boolean = false
) : BaseMviViewState