package com.serma.dionysus.common.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.serma.dionysus.R

@Preview
@Composable
private fun CommonButtonPreview() {
    val gradient = Brush.horizontalGradient(listOf(Color(0xFF432DD4), Color(0xFF7180B9)))
    Column {
        CommonGradientButton(R.string.example, gradient, {})
    }
}

@Composable
fun CommonGradientButton(
    @StringRes textId: Int,
    gradient: Brush,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(40)
) {
    Button(
        modifier = modifier
            .background(gradient, shape),
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