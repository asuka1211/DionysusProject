package com.serma.dionysus.ui.graph

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.serma.dionysus.R
import com.serma.dionysus.common.ui.*
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun GraphScreen(
    logout: () -> Unit,
    openProfile: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: GraphViewModel
) {
    val pagerState = rememberPagerState(pageCount = 2, initialPage = 0)

    val tabs = listOf(
        GraphTabs(R.string.graph_budget, 0, GraphTabsType.BUDGET),
        GraphTabs(R.string.graph_status, 1, GraphTabsType.STATUS)
    )

    var firstLoad = remember {
        mutableStateOf(true)
    }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CommonTopAppBar(
                logout = logout,
                navigateBack = navigateBack,
                openProfile = openProfile
            )
        },
        content = {
            Column {
                TabRow(
                    pagerState.currentPage,
                    indicator = { tabPos ->
                        TabRowDefaults.Indicator(
                            Modifier.pagerTabIndicatorOffset(pagerState, tabPos)
                        )
                    }
                ) {
                    tabs.forEachIndexed { index, item ->
                        Box(modifier = Modifier.height(48.dp)) {
                            Tab(
                                selected = pagerState.currentPage == index,
                                modifier = Modifier
                                    .align(Alignment.Center),
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                }
                            ) {
                                Text(text = stringResource(id = item.textId), color = Color.White)
                            }
                        }
                    }
                }
                SpacerRow(height = 16)
                HorizontalPager(state = pagerState) { page ->
                    if (firstLoad.value) {
                        firstLoad.value = false
                        return@HorizontalPager
                    }
                    when (page) {
                        0 -> BudgetScreen(viewModel)
                        1 -> StatusScreen(viewModel)
                    }
                }
            }
        }
    )
}

@Composable
fun BudgetScreen(viewModel: GraphViewModel) {
    val state = viewModel.uiState.collectAsState()
    if (!state.value.loading && state.value.dataBudget == null) {
        viewModel.loadBudget()
    }
    CommonBaseStateScreen(
        state = state,
        reload = {
            viewModel.loadBudget()
        }
    ) {
        state.value.dataBudget?.let {
            Column {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.all_budget, it.allBudget)
                )
                SpacerRow(height = 16)
                GraphBudget(list = it.list)
            }
        }
    }
}

@Composable
fun StatusScreen(viewModel: GraphViewModel) {
    val state = viewModel.uiState.collectAsState()
    if (!state.value.loading && state.value.dataStatus == null) {
        viewModel.loadStatus()
    }
    CommonBaseStateScreen(
        state = state,
        reload = {
            viewModel.loadStatus()
        }
    ) {
        state.value.dataStatus?.let {
            GraphStatus(list = it)
        }
    }
}

@Composable
fun GraphStatus(list: List<GraphItem>) {
    InfoForGraph(
        GraphData(list),
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        donutSize = 200.dp
    ) {
        Text(text = "${it.name} ${it.progress}%")
    }
}

@Composable
fun GraphBudget(list: List<GraphItemBudget>) {
    InfoForGraph(
        GraphData(list),
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        donutSize = 200.dp
    ) {
        Text(text = "${it.name} ${it.budget} ${it.progress}%")
    }
}


data class GraphTabs(
    val textId: Int,
    override val pos: Int,
    override val type: GraphTabsType
) : BaseTabItem<GraphTabsType>

enum class GraphTabsType {
    BUDGET, STATUS
}

data class GraphItem(
    override val progress: Float,
    override val color: Color,
    val name: String
) : SliceData

data class GraphItemBudget(
    override val progress: Float,
    override val color: Color,
    val name: String,
    val budget: String
) : SliceData

data class BudgetData(val allBudget: String, val list: List<GraphItemBudget>)