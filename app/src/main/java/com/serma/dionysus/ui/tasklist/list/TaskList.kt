package com.serma.dionysus.ui.tasklist.list

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serma.dionysus.R
import com.serma.dionysus.common.theme.BackgroundInputColor
import com.serma.dionysus.common.ui.TaskCardRow

@Preview
@Composable
fun TaskListPreview() {
}

@ExperimentalMaterialApi
@Composable
fun TaskCardsHolder(
    eventId: String,
    stateId: String,
    onTaskClicked: (String) -> Unit,
    tasksViewModel: TasksViewModel,
    reload: () -> Unit
) {
    val state = tasksViewModel.uiState.collectAsState()

    tasksViewModel.load(eventId, stateId)

    if (state.value.loading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        state.value.data[stateId]?.let { data ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
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
                    LazyColumn {
                        itemsIndexed(data.tasks) { pos, task ->
                            TaskCard(task, onTaskClicked, tasksViewModel, stateId, eventId, reload)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun TaskCard(
    task: TaskListData,
    onClick: (String) -> Unit,
    viewModel: TasksViewModel,
    stateId: String,
    eventId: String,
    reload: () -> Unit
) {

    val dismissState = rememberDismissState(DismissValue.Default) {
        it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart
    }

    val directions = when (task.taskPageInfo.directions) {
        DirectionsSwipeTask.LEFT -> setOf(DismissDirection.EndToStart)
        DirectionsSwipeTask.RIGHT -> setOf(DismissDirection.StartToEnd)
        DirectionsSwipeTask.LEFT_AND_RIGHT -> setOf(
            DismissDirection.StartToEnd,
            DismissDirection.EndToStart
        )
    }

    SwipeToDismiss(state = dismissState,
        modifier = Modifier.padding(vertical = 4.dp),
        directions = directions,
        dismissThresholds = {
            FractionalThreshold(0.25f)
        },
        background = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> Color.LightGray
                    DismissValue.DismissedToEnd -> task.taskPageInfo.rightColor ?: Color.LightGray
                    DismissValue.DismissedToStart -> task.taskPageInfo.leftColor ?: Color.LightGray
                }
            )

            Box(
                Modifier
                    .fillMaxSize()
                    .background(color, RoundedCornerShape(10.dp))
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    ) {
        when (dismissState.currentValue) {
            DismissValue.DismissedToEnd -> {
                viewModel.move(task.id, task.taskPageInfo.rightId ?: "", stateId)
                reload()
            }
            DismissValue.DismissedToStart -> {
                viewModel.move(task.id, task.taskPageInfo.leftId ?: "", stateId)
                reload()
            }
        }
        if (!task.parentTaskName.isNullOrEmpty()) {
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
                        task.parentTaskName,
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
        } else {
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
    val parentTaskName: String?,
    val taskPageInfo: TaskPageInfo
)

data class TaskPageInfo(
    val leftColor: Color?,
    val rightColor: Color?,
    val directions: DirectionsSwipeTask,
    val leftId: String?,
    val rightId: String?
)

enum class DirectionsSwipeTask {
    LEFT, RIGHT, LEFT_AND_RIGHT
}

