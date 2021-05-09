package com.serma.dionysus.common.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serma.dionysus.common.theme.BackgroundInputColor
import com.serma.dionysus.common.theme.DionysusTheme
import com.serma.dionysus.R
import com.serma.dionysus.common.theme.SecondaryColor
import com.serma.dionysus.ui.tasklist.TaskData

@Preview
@Composable
private fun CommonInputPreview() {
    DionysusTheme {
        Column {
            //CommonTextField(R.string.example) {}
            Spacer(modifier = Modifier.height(10.dp))
            CommonTextFieldWithTitle(R.string.example, R.string.example) {}
            Spacer(modifier = Modifier.height(10.dp))
//            CommonPasswordTextField(R.string.example) {}
//            Spacer(modifier = Modifier.height(10.dp))
//            CommonTextWithTitleClickable(R.string.example, R.string.example, "sad", {})
//            ReadOnlyTextFieldWithTitle(R.string.auth_hint_email, "Test")
//            Spacer(modifier = Modifier.height(10.dp))
//            DropDownMenu(2)
            val testData = TaskData(
                "Тупое говно",
                "Тупого говна",
                "Вчера",
                "Родительская задача"
            )
            val listTestData = listOf(testData, testData)
            TaskCardWithParent(testData)
            TaskCardsHolder(R.string.in_discussion, listTestData)
        }
    }
}

@Composable
fun CommonTextField(
    @StringRes hintTextId: Int,
    onValueChange: ((String) -> Unit)? = null,
    text: String = "",
    modifier: Modifier = Modifier
) {
    val textState = remember {
        mutableStateOf(text)
    }
    TextField(
        value = textState.value,
        onValueChange = {
            textState.value = it
            onValueChange?.invoke(it)
        },
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = BackgroundInputColor,
        ),
        singleLine = true,
        textStyle = MaterialTheme.typography.subtitle2,
        placeholder = {
            Text(text = stringResource(id = hintTextId))
        }
    )
}

@Composable
fun CommonTextFieldWithTitle(
    @StringRes titleTextId: Int,
    @StringRes hintTextId: Int,
    forPassword: Boolean = false,
    text: String = "",
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            stringResource(titleTextId),
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.subtitle2,
        )
        if (forPassword) {
            CommonPasswordTextField(hintTextId, onValueChange)
        } else {
            CommonTextField(hintTextId, onValueChange, text)
        }
    }
}

@Composable
fun CommonTextWithTitleClickable(
    @StringRes titleTextId: Int,
    @StringRes hintTextId: Int,
    text: String,
    onClick: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            stringResource(titleTextId),
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.subtitle2,
        )
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick?.invoke() },
            enabled = false,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = BackgroundInputColor,
            ),
            singleLine = true,
            textStyle = MaterialTheme.typography.subtitle2,
            placeholder = {
                if (text.isEmpty()) {
                    Text(text = stringResource(id = hintTextId), color = Color.DarkGray)
                } else {
                    Text(text = text, color = Color.Black)
                }
            }
        )

    }
}

@Composable
fun CommonPasswordTextField(
    @StringRes hintTextId: Int,
    onValueChange: (String) -> Unit
) {
    val text = remember {
        mutableStateOf("")
    }
    TextField(
        value = text.value,
        onValueChange = {
            text.value = it
            onValueChange(it)
        },
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = BackgroundInputColor,
        ),
        singleLine = true,
        textStyle = MaterialTheme.typography.subtitle2,
        placeholder = {
            Text(text = stringResource(id = hintTextId))
        }
    )
}

@Composable
fun ReadOnlyTextField(innerText: String) {
    TextField(
        value = "",
        onValueChange = {},
        enabled = false,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = BackgroundInputColor,
        ),
        singleLine = true,
        textStyle = MaterialTheme.typography.subtitle2,
        placeholder = {
            Text(text = innerText, color = Color.Black)

        }
    )
}

@Composable
fun ReadOnlyTextFieldWithTitle(
    @StringRes titleTextId: Int,
    innerText: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            stringResource(titleTextId),
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.subtitle2,
        )
        ReadOnlyTextField(innerText)
    }
}

@Composable
fun TaskCardRow(@StringRes leftText: Int, rightText: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            stringResource(leftText),
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.subtitle2,
        )
        Text(
            rightText,
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.subtitle2,
        )
    }
}

@Composable
fun TaskCard(task: TaskData) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            TaskCardRow(R.string.tag, task.tag)
            TaskCardRow(R.string.name, task.name)
            TaskCardRow(R.string.task_deadline, task.taskDeadlide)
        }
    }
}

@Composable
fun TaskCardWithParent(task: TaskData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(8.dp)
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
                TaskCardRow(R.string.task_deadline, task.taskDeadlide)
            }
        }
    }
}

@Composable
fun TaskCardsHolder(@StringRes titleTextId: Int, tasks: List<TaskData>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(BackgroundInputColor)
            .padding(8.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                stringResource(titleTextId),
                modifier = Modifier.padding(vertical = 8.dp),
                style = MaterialTheme.typography.subtitle1,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(44.dp, 18.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Green)
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 20.dp)
            )
        }
        tasks.forEach { task ->
            if (task.parentTaskName == "") TaskCard(task)
            else TaskCardWithParent(task)
            Spacer(modifier = Modifier.height(8.dp))
        }


        AddingButton(buttonTextId = R.string.add_task, Color.White)
    }
}

@Composable
fun DropDownMenu(selectionNumber: Int) {
    val suggestions = listOf("Срочная задача", "Средная срочность", "Несрочная задача")
    val initialSelectedText = if (selectionNumber == 0) ""
    else suggestions[selectionNumber - 1]

    val expanded = remember { mutableStateOf(false) }
    val selectedText = remember { mutableStateOf(initialSelectedText) }

    Column() {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = selectedText.value,
            onValueChange = {},
            enabled = false,
            shape = RoundedCornerShape(10.dp),
            textStyle = MaterialTheme.typography.subtitle2,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = BackgroundInputColor,
            ),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { expanded.value = !expanded.value }) {
                    Icon(Icons.Filled.ArrowDropDown, null)
                }
            }

        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            suggestions.forEachIndexed { index, label ->
                DropdownMenuItem(onClick = {
                    selectedText.value = label
                    expanded.value = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}

@Composable
fun DropDownMenuWithTitle(@StringRes titleTextId: Int, selectionNumber: Int) {
    Column {
        Text(
            stringResource(titleTextId),
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.subtitle2,
        )
        DropDownMenu(selectionNumber)
    }
}