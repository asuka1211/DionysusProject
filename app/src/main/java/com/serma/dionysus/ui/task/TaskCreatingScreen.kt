package com.serma.dionysus.ui.task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serma.dionysus.R
import com.serma.dionysus.common.ui.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

//private val saveColor = Brush.horizontalGradient(listOf(Color(0xFF432DD4), Color(0xFF7180B9)))
//private val deleteColor = Brush.horizontalGradient(listOf(Color(0xFFFF0000), Color(0xFFFF4040)))


@Preview
@Composable
fun TaskCreatingScreenPreview() {
    val testData = PersonData(
        "1",
        "Максим Яковлев",
        "https://s0.rbk.ru/v6_top_pics/media/img/5/46/756038770746465.jpg"
    )
    val listTestData = listOf(testData, testData)

//    val tagList = listOf("первый", "второй", "третий")
//
//    val data = AddingTaskData(
//        1,
//        "Создание приложения",
//        "Завтра",
//        "Обсуждение необходимых технологий",
//        testData,
//        listTestData,
//        "первый",
//        "Срочная задача"
//    )
//    TaskCreatingScreen(data)
}

@ExperimentalCoroutinesApi
@Composable
fun TaskCreatingScreen(
    taskId: String,
    eventId: String,
    viewModel: TaskViewModel,
    logout: () -> Unit,
    openProfile: () -> Unit,
    navigateBack: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()

    viewModel.load(eventId, taskId)

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
                    viewModel.reload(eventId, taskId)
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

//                        UserCardWithTitle(
//                            titleTextId = R.string.task_author,
//                            person = data.author
//                        )
//
//                        UserCardsHolderWithTitle(
//                            titleTextId = R.string.responsible_for_task,
//                            data = data.accountable
//                        )

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

//            CommonTextFieldWithTitle(
//                titleTextId = R.string.task_name,
//                hintTextId = R.string.task_name_hint,
//                onValueChange = {}
//            )
//
//            CommonTextFieldWithTitle(
//                titleTextId = R.string.task_deadline,
//                hintTextId = R.string.task_deadline_hint,
//                onValueChange = {}
//            )
//
//            CommonTextFieldWithTitle(
//                titleTextId = R.string.task_description,
//                hintTextId = R.string.task_description_hint,
//                onValueChange = {}
//            )
//
//            AddingUserCardsWithTitle(
//                titleTextId = R.string.responsible_for_task,
//                buttonTextId = R.string.add_responsible,
//                data = data.accountable
//            )
//
//            val suggestions = listOf(
//                ImportanceData(1, stringResource(R.string.urgent_task)),
//                ImportanceData(2, stringResource(R.string.average_urgency_task)),
//                ImportanceData(3, stringResource(R.string.non_urgent_task))
//            )
//            DropDownMenuWithTitle(
//                R.string.importance,
//                suggestions,
//                data.importance,
//            ) { item, onClick ->
//                DropdownMenuItem(onClick = { onClick(item) }) {
//                    Text(
//                        item.name,
//                        modifier = Modifier.padding(vertical = 8.dp),
//                        style = MaterialTheme.typography.subtitle2,
//                    )
//                }
//            }
//            DropDownMenuWithTitle(R.string.tag, data.importanceNumber)
//            AddingTagWithTitle(
//                titleTextId = R.string.tag,
//                buttonTextId = R.string.add_tag,
//                tags = data.tags
//            )
//            AddingButton(buttonTextId = R.string.add_tag, BackgroundInputColor, onClick = {})
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            CommonGradientButton(
//                gradient = saveColor,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(56.dp),
//                textId = R.string.save,
//                onClick = {}
//            )
//            if (data.id != 0) {
//                CommonGradientButton(
//                    gradient = deleteColor,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(56.dp),
//                    textId = R.string.delete,
//                    onClick = {}
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//            }
        }
    )
}


data class AddingTaskData(
    val id: Int,
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

