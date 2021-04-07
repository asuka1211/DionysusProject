package com.serma.common_ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SpacerRow(height: Int){
    Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun CommonImage(modifier: Modifier = Modifier, @DrawableRes id: Int) {
    Image(painter = painterResource(id), contentDescription = null, modifier = modifier)
}