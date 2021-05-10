package com.serma.dionysus.ui.tasklist.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.serma.dionysus.R
import com.serma.dionysus.common.theme.BackgroundInputColor
import com.serma.dionysus.common.ui.AddingButton
import com.serma.dionysus.common.ui.TaskCardRow

@Preview
@Composable
fun TaskListPreview() {
}

@Composable
fun TaskCardsHolder(
    eventId: String,
    stateId: String,
    addOnClick: () -> Unit,
    onTaskClicked: (String) -> Unit,
    tasksViewModel: TasksViewModel,
) {
    val state = tasksViewModel.uiState.collectAsState()

    if (state.value.data[stateId] == null) {
        tasksViewModel.load(eventId, stateId)
    }

    if (state.value.loading && state.value.data[stateId] == null) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        state.value.data[stateId]?.let { data ->
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(BackgroundInputColor)
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            data.name,
                            modifier = Modifier.padding(vertical = 8.dp),
                            style = MaterialTheme.typography.subtitle1,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .size(44.dp, 18.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(data.color)
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 20.dp)
                        )
                    }
                    data.tasks.forEach { task ->
                        if (task.parentTaskName.isNullOrEmpty()) {
                            TaskCard(task, onTaskClicked)
                        } else {
                            TaskCardWithParent(task, onTaskClicked)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    AddingButton(buttonTextId = R.string.add_task, Color.White, addOnClick)
                }
            }
        }
    }
}

@Composable
fun TaskCard(task: TaskListData, onClick: (String) -> Unit) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .clickable { onClick(task.id) }
        ) {
            TaskCardRow(R.string.tag, task.tag)
            TaskCardRow(R.string.name, task.name)
            TaskCardRow(R.string.task_deadline, task.taskDeadline)
        }
    }
}

@Composable
fun TaskCardWithParent(task: TaskListData, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(8.dp)
            .clickable { onClick(task.id) }
    ) {
        Column {
            Text(
                task.parentTaskName ?: "",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.subtitle1,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(BackgroundInputColor)
            ) {
                TaskCardRow(R.string.tag, task.tag)
                TaskCardRow(R.string.name, task.name)
                TaskCardRow(R.string.task_deadline, task.taskDeadline)
            }
        }
    }
}


data class TasksTypeData(
    val typeId: String,
    val name: String,
    val color: Color,
    val tasks: List<TaskListData>
)

data class TaskListData(
    val id: String,
    val tag: String,
    val name: String,
    val taskDeadline: String,
    val parentTaskName: String?
)

