package com.serma.dionysus.ui.tasklist.pager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.serma.dionysus.common.ui.CommonBaseStateScreen
import com.serma.dionysus.common.ui.CommonTopAppBar
import com.serma.dionysus.ui.tasklist.list.TaskCardsHolder
import com.serma.dionysus.ui.tasklist.list.TasksViewModel


@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun TaskPager(
    eventId: String,
    viewModel: TaskPageViewModel,
    openProfile: () -> Unit,
    openTask: (String) -> Unit,
    logout: () -> Unit,
    navigateBack: () -> Unit,
    tasksViewModel: TasksViewModel
) {
    val state = viewModel.uiState.collectAsState()

    if (state.value.data == null) {
        viewModel.load(eventId)
    }

    val pagerState = rememberPagerState(pageCount = state.value.data?.pagesIds?.size ?: 0)

    Scaffold(
        topBar = {
            CommonTopAppBar(
                logout = logout,
                navigateBack = { navigateBack() },
                openProfile = openProfile
            )
        },
        content = {
            CommonBaseStateScreen(
                state = state,
                reload = {
                    viewModel.reload(eventId)
                }
            ) {
                state.value.data?.pagesIds?.let { ids ->
                    Box(Modifier.fillMaxSize()) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxSize(),
                        ) { page ->
                            TaskCardsHolder(
                                eventId,
                                ids[page],
                                { id -> openTask(id) },
                                tasksViewModel,
                                { viewModel.reload(eventId) }
                            )
                        }
                        HorizontalPagerIndicator(
                            pagerState = pagerState,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(16.dp),
                        )
                    }
                }
            }
        }
    )
}

data class TaskPagerData(
    val pagesIds: List<String>
)
