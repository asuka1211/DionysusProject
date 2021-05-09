package com.serma.dionysus.common.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Preview
@Composable
private fun CommonInputPreview() {
    DionysusTheme {
        Column {
            //CommonTextField(R.string.example) {}
            Spacer(modifier = Modifier.height(10.dp))
            CommonTextFieldWithTitle(R.string.example, R.string.example, ){}
            Spacer(modifier = Modifier.height(10.dp))
            CommonPasswordTextField(R.string.example) {}
            Spacer(modifier = Modifier.height(10.dp))
            CommonTextWithTitleClickable(R.string.example, R.string.example, "sad", {})
            ReadOnlyTextFieldWithTitle(R.string.auth_hint_login, "Test")
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
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(),
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
}