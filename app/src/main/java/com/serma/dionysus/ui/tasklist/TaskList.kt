package com.serma.dionysus.ui.tasklist

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.serma.dionysus.R
import com.serma.dionysus.common.theme.BackgroundColor
import com.serma.dionysus.common.theme.BackgroundInputColor
import com.serma.dionysus.common.ui.*

private val color = Brush.horizontalGradient(listOf(Color(0xFF432DD4), Color(0xFF7180B9)))


@Preview
@Composable
fun TaskListPreview() {
    val testData = TaskData(
        "Тупое говно",
        "Тупого говна",
        "Вчера",
        ""
    )
    val testData2 = TaskData(
        "Тупое говно",
        "Тупого говна",
        "Вчера",
        "Родительская задача"
    )
    val listTestData = listOf(testData, testData2, testData)
    TaskList(listTestData)
}

@Composable
fun TaskList(data: List<TaskData>) {
    Surface(modifier = Modifier.background(BackgroundColor)) {
        Column(
            modifier = Modifier
                .wrapContentHeight(align = Top)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp)
        ) {
            TaskCardsHolder(R.string.in_discussion, data)
        }
    }
}

data class TaskData(
    val tag: String,
    val name: String,
    val taskDeadlide: String,
    val parentTaskName: String
)

