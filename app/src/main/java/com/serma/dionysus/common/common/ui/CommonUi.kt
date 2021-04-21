package com.serma.dionysus.common.common.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import com.google.accompanist.glide.GlideImage

@Preview
@Composable
fun PreviewCommon() {
    PersonItem(
        "https://static7.depositphotos.com/1314241/789/i/600/depositphotos_7890698-stock-photo-ferocious-lion.jpg",
        "лев ебать"
    )
}

@Composable
fun SpacerRow(height: Int) {
    Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun CommonImage(@DrawableRes id: Int, modifier: Modifier = Modifier) {
    Image(painter = painterResource(id), contentDescription = null, modifier = modifier)
}

@Composable
fun PersonItem(url: String, name: String, modifier: Modifier = Modifier) {
    Row {
        GlideImage(
            data = url,
            modifier = Modifier
                .height(48.dp)
                .width(48.dp),
            requestBuilder = {
                val options = RequestOptions()
                options.circleCrop()

                apply(options)
            },
            contentDescription = "null",
            loading = {
                Box(Modifier.matchParentSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            },
            fadeIn = true
        )
        Text(
            text = name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(CenterVertically)
        )
    }
}