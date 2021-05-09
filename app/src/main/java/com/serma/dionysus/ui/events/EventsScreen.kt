package com.serma.dionysus.ui.events

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serma.dionysus.common.theme.BackgroundColor
import com.serma.dionysus.common.theme.LoadColor
import com.serma.dionysus.common.theme.PrimaryColor
import com.serma.dionysus.common.ui.*

@Preview
@Composable
fun EventsScreenPreview() {
}

@Composable
fun EventsScreen(
    openProfile: () -> Unit,
    openEvent: (String) -> Unit,
    logout: () -> Unit,
    viewModel: EventsViewModel
) {
    Scaffold(
        topBar = { CommonTopAppBar(openProfile = openProfile, logout = logout) }
    ) {
        val state = viewModel.uiState.collectAsState()
        CommonBaseStateScreen(
            state = state,
            reload = {
                viewModel.reload()
            }
        ) {
            state.value.events?.let {
                LazyColumn {
                    pagingList(
                        it,
                        state.value.loadingMore,
                        loadMore = {
                            viewModel.loadMore(
                                state.value.pageNumber,
                                state.value.pageSize
                            )
                        },
                        itemContent = { _, event ->
                            EventCard(event, openEvent)
                        }) {
                        Box(Modifier.fillMaxWidth()) {
                            CircularProgressIndicator(
                                Modifier
                                    .size(60.dp)
                                    .padding(8.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EventCard(data: EventData, onClick: (String) -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .requiredHeight(200.dp)
            .padding(8.dp)
            .clickable { onClick(data.id) },
        elevation = 8.dp
    ) {
        Column {
            Column(
                Modifier
                    .requiredHeight(160.dp)
                    .background(PrimaryColor)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Row(
                    Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = data.location,
                        Modifier.weight(1f),
                        color = BackgroundColor,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = data.date,
                        Modifier.weight(1f),
                        color = BackgroundColor,
                        textAlign = TextAlign.Center
                    )
                }
                LinearProgressIndicator(
                    progress = data.progress,
                    Modifier
                        .requiredHeight(16.dp)
                        .weight(1f)
                        .fillMaxWidth(),
                    color = LoadColor,
                    backgroundColor = BackgroundColor,
                )
            }
            Text(
                text = data.name,
                Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp, end = 16.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

data class EventData(
    val id: String,
    val location: String,
    val date: String,
    val name: String,
    val progress: Float
)