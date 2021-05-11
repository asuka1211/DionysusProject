package com.serma.dionysus.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serma.dionysus.R
import com.serma.dionysus.common.ui.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Preview
@Composable
fun TaskCreatingScreenPreview() {
    val testData = PersonData(
        "1",
        "Максим Яковлев",
        "https://s0.rbk.ru/v6_top_pics/media/img/5/46/756038770746465.jpg"
    )
    val listTestData = listOf(testData, testData)

}

@ExperimentalCoroutinesApi
@Composable
fun TaskCreatingScreen(
    taskId: String,
    viewModel: TaskViewModel,
    logout: () -> Unit,
    openProfile: () -> Unit,
    navigateBack: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()

    viewModel.load(taskId)

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
                    viewModel.reload(taskId)
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
                            titleTextId = R.string.task_name,
                            innerText = data.name,
                        )

                        ReadOnlyTextFieldWithTitle(
                            titleTextId = R.string.task_deadline,
                            innerText = data.date,
                        )

                        ReadOnlyTextFieldWithTitle(
                            titleTextId = R.string.task_description,
                            innerText = data.description,
                        )

                        UserCardsHolderWithTitle(R.string.task_author, listOf(data.author))

                        UserCardsHolderWithTitle(R.string.responsible_for_task, data.accountable)

                        ReadOnlyTextFieldWithTitle(
                            titleTextId = R.string.tag,
                            innerText = data.tag,
                        )

                        ReadOnlyTextFieldWithTitle(
                            titleTextId = R.string.importance,
                            innerText = data.importance,
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    )
}


data class AddingTaskData(
    val id: String,
    val name: String,
    val date: String,
    val description: String,
    val author: PersonData,
    val accountable: List<PersonData>,
    val tag: String,
    val importance: String
)

data class ImportanceData(
    val id: Int,
    val name: String,
)

data class TagData(
    val id: String,
    val name: String,
)

