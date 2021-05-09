package com.serma.dionysus.ui.eventinfo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serma.dionysus.R
import com.serma.dionysus.common.ui.*

@Preview
@Composable
fun EventInfoScreenPreview() {
    val testData = PersonData(
        "Максим Яковлев",
        "https://s0.rbk.ru/v6_top_pics/media/img/5/46/756038770746465.jpg"
    )
    val listTestData = listOf(testData, testData, testData, testData)

    val data = EventInfoData(
        "Сдерживание грузина",
        "Завтра",
        "Сдерживание мощного, не молодого грузина посредством применения специально оборудованных водометов",
        listTestData,
        "Политех",
        "300$"
    )
//    EventInfoScreen(data)
}

@Composable
fun EventInfoScreen(
    eventId: String,
    viewModel: EventInfoViewModel,
    openGraph: (String) -> Unit,
    logout: () -> Unit,
    openProfile: () -> Unit,
    navigateBack: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()

    viewModel.load(eventId)

    Scaffold(
        topBar = {
            CommonTopAppBar(
                logout = logout,
                navigateBack = navigateBack,
                openProfile = openProfile
            )
        },
        content = {
            CommonBaseStateScreen(
                state = uiState,
                reload = {
                    viewModel.reload(eventId)
                }
            ) {
                uiState.value.data?.let { data ->
                    Column(
                        modifier = Modifier
                            .wrapContentHeight(align = Top)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(start = 16.dp, end = 16.dp)
                    ) {
                        ReadOnlyTextFieldWithTitle(
                            titleTextId = R.string.event_name,
                            innerText = data.name,
                        )
                        ReadOnlyTextFieldWithTitle(
                            titleTextId = R.string.event_date,
                            innerText = data.date,
                        )
                        ReadOnlyTextFieldWithTitle(
                            titleTextId = R.string.event_description,
                            innerText = data.description,
                        )
                        UserCardsHolderWithTitle(
                            titleTextId = R.string.manager,
                            data = data.managerData
                        )
                        ReadOnlyTextFieldWithTitle(
                            titleTextId = R.string.event_location,
                            innerText = data.location,
                        )
                        ReadOnlyTextFieldWithTitle(
                            titleTextId = R.string.budget,
                            innerText = data.budget,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CommonGradientButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            textId = R.string.open_graph,
                            onClick = { openGraph(eventId) })
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    )
}

data class EventInfoData(
    val name: String,
    val date: String,
    val description: String,
    val managerData: List<PersonData>,
    val location: String,
    val budget: String
)
