package com.serma.dionysus.common.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serma.dionysus.R
import com.serma.dionysus.common.theme.BackgroundInputColor

@Preview
@Composable
private fun CommonButtonPreview() {
    val gradient = Brush.horizontalGradient(listOf(Color(0xFF432DD4), Color(0xFF7180B9)))
    Column {
        CommonGradientButton(R.string.example, gradient, {})
        Spacer(modifier = Modifier.height(16.dp))
        //AddingButton(buttonTextId = R.string.add_author, BackgroundInputColor)
    }
}

private val brush = Brush.horizontalGradient(listOf(Color(0xFF432DD4), Color(0xFF7180B9)))

@Composable
fun CommonGradientButton(
    @StringRes textId: Int,
    gradient: Brush = brush,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(40)
) {
    Button(
        modifier = modifier
            .background(gradient, shape)
            .clip(shape),
        colors = ButtonDefaults.buttonColors(
            Color.Transparent,
            Color.Transparent,
            Color.Transparent,
            Color.Transparent
        ),
        onClick = { onClick() },
        elevation = null
    ) {
        Text(
            text = stringResource(id = textId),
            color = Color.White,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
fun AddingButton(@StringRes buttonTextId: Int, backgroundColor: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(horizontal = 0.dp, vertical = 8.dp).fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor,
            Color.Black,
            Color.Transparent,
            Color.Transparent
        ),
    ) {
        Box{
            Row {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = stringResource(buttonTextId)

                )
            }
        }
    }
}
