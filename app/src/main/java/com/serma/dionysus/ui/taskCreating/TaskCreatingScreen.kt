package com.serma.dionysus.ui.taskCreating

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serma.dionysus.R
import com.serma.dionysus.common.theme.BackgroundColor
import com.serma.dionysus.common.ui.*
import com.serma.dionysus.ui.eventinfo.EventInfoData


@Preview
@Composable
fun TaskCreatingScreenPreview() {
    val testData = PersonData(
        "Максим Яковлев",
        "https://s0.rbk.ru/v6_top_pics/media/img/5/46/756038770746465.jpg"
    )
    val listTestData = listOf(testData, testData)

    val data = EventInfoData(
        "Сдерживание грузина",
        "Завтра",
        "Сдерживание мощного, не молодого грузина посредством применения специально оборудованных водометов",
        listTestData,
        "Политех",
        "300$"
    )
    TaskCreatingScreen(data)
}

@Composable
fun TaskCreatingScreen(data: EventInfoData) {
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
                titleTextId = R.string.task_name,
                hintTextId = R.string.task_name_hint,
                onValueChange = {}
            )
            AddingButton(
                buttonTextId = R.string.add_author
            )

            AddingUserCardsWithTitle(
                titleTextId = R.string.task_author,
                buttonTextId = R.string.add_author,
                data = data.managerData
            )

            AddingUserCardsWithTitle(
                titleTextId = R.string.responsible_for_task,
                buttonTextId = R.string.add_responsible,
                data = data.managerData
            )

//            AddingButtonWithTitle(
//                titleTextId = R.string.tag,
//                buttonTextId = R.string.add_tag
//            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

