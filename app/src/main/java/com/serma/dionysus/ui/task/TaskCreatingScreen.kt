package com.serma.dionysus.ui.taskCreating

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serma.dionysus.R
import com.serma.dionysus.common.theme.BackgroundColor
import com.serma.dionysus.common.ui.*

private val saveColor = Brush.horizontalGradient(listOf(Color(0xFF432DD4), Color(0xFF7180B9)))
private val deleteColor = Brush.horizontalGradient(listOf(Color(0xFFFF0000), Color(0xFFFF4040)))


@Preview
@Composable
fun TaskCreatingScreenPreview() {
    val testData = PersonData(
        "Максим Яковлев",
        "https://s0.rbk.ru/v6_top_pics/media/img/5/46/756038770746465.jpg"
    )
    val listTestData = listOf(testData, testData)

    val tagList = listOf("первый", "второй", "третий")

    val data = AddingTaskData(
        1,
        "Сдерживание грузина",
        "Завтра",
        "Сдерживание мощного, не молодого грузина посредством применения специально оборудованных водометов",
        testData,
        listTestData,
        tagList,
        2
    )
    TaskCreatingScreen(data)
}

@Composable
fun TaskCreatingScreen(data: AddingTaskData) {
    Surface(modifier = Modifier.background(BackgroundColor)) {
        Column(
            modifier = Modifier
                .wrapContentHeight(align = Top)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp)
        ) {
            CommonTextFieldWithTitle(
                titleTextId = R.string.task_name,
                hintTextId = R.string.task_name_hint,
                onValueChange = {}
            )

            CommonTextFieldWithTitle(
                titleTextId = R.string.task_deadline,
                hintTextId = R.string.task_deadline_hint,
                onValueChange = {}
            )

            CommonTextFieldWithTitle(
                titleTextId = R.string.task_description,
                hintTextId = R.string.task_description_hint,
                onValueChange = {}
            )

            UserCardWithTitle(
                titleTextId = R.string.task_author,
                person = data.author
            )

            AddingUserCardsWithTitle(
                titleTextId = R.string.responsible_for_task,
                buttonTextId = R.string.add_responsible,
                data = data.accountable
            )

            DropDownMenuWithTitle(R.string.importance, data.importanceNumber)

            AddingTagWithTitle(
                titleTextId = R.string.tag,
                buttonTextId = R.string.add_tag,
                tags = data.tags
            )

            Spacer(modifier = Modifier.height(16.dp))

            CommonGradientButton(
                gradient = saveColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                textId = R.string.save,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (data.id != 0) {
                CommonGradientButton(
                    gradient = deleteColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    textId = R.string.delete,
                    onClick = {}
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

data class AddingTaskData(
    val id: Int,
    val name: String,
    val date: String,
    val description: String,
    val author: PersonData,
    val accountable: List<PersonData>,
    val tags: List<String>,
    val importanceNumber: Int
)

