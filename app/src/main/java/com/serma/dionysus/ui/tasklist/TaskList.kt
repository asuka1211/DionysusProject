package com.serma.dionysus.ui.tasklist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serma.dionysus.R
import com.serma.dionysus.common.theme.BackgroundColor
import com.serma.dionysus.common.ui.AddingButton
import com.serma.dionysus.common.ui.TaskCardsHolder

@Preview
@Composable
fun TaskListPreview() {
    val testData = TaskData(
        "Привер тэга",
        "Пример имени",
        "Сегодня",
        "",
        1
    )
    val testData2 = TaskData(
        "Привер тэга",
        "Пример имени2",
        "Сегодня",
        "",
        1
    )
    val listTestData = listOf(testData, testData2, testData)
    TaskList(listTestData, 1)
}

@Composable
fun TaskList(data: List<TaskData>, pageNum: Int) {
    Surface(modifier = Modifier.background(BackgroundColor)) {
        Column(
            modifier = Modifier
                .wrapContentHeight(align = Top)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp)
        ) {
            when (pageNum) {
                0 -> {
                    TaskCardsHolder(R.string.in_discussion, data, pageNum)
                    AddingButton(buttonTextId = R.string.add_task, Color.White)
                }
                1 -> {
                    TaskCardsHolder(R.string.in_progress, data, pageNum)
                    AddingButton(buttonTextId = R.string.add_task, Color.White)
                }
                else -> {
                    TaskCardsHolder(R.string.done, data, pageNum)
                    AddingButton(buttonTextId = R.string.add_task, Color.White)
                }
            }
        }
    }
}

data class TaskData(
    val tag: String,
    val name: String,
    val taskDeadlide: String,
    val parentTaskName: String,
    val pageNum: Int
)

