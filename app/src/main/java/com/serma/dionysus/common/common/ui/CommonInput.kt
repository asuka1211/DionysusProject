package com.serma.dionysus.common.common.ui

import androidx.annotation.StringRes
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

@Preview
@Composable
private fun CommonInputPreview() {
    DionysusTheme {
        Column {
            CommonTextField(R.string.example) {}
            Spacer(modifier = Modifier.height(10.dp))
            CommonTextFieldWithTitle(R.string.example, R.string.example, {})
            Spacer(modifier = Modifier.height(10.dp))
            CommonPasswordTextField(R.string.example) {}
        }
    }
}


@Composable
fun CommonTextField(
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
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = BackgroundInputColor,
        ),
        singleLine = true,
        textStyle = MaterialTheme.typography.subtitle2,
        placeholder = { Text(text = stringResource(id = hintTextId)) }
    )
}

@Composable
fun CommonTextFieldWithTitle(
    @StringRes titleTextId: Int,
    @StringRes hintTextId: Int,
    onValueChange: (String) -> Unit,
    forPassword: Boolean = false
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
            CommonTextField(hintTextId, onValueChange)
        }
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
        placeholder = { Text(text = stringResource(id = hintTextId)) }
    )
}