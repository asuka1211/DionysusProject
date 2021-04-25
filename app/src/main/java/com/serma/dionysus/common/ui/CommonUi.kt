package com.serma.dionysus.common.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.request.RequestOptions
import com.google.accompanist.glide.GlideImage
import com.serma.dionysus.common.theme.BackgroundInputColor

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

@Composable
fun UserNameAndAvatar(name: String, url: String) {
    Card(shape = RoundedCornerShape(16.dp), elevation = 0.dp) {
        Row(
            modifier = Modifier
                .background(color = BackgroundInputColor)
                .padding(start = 16.dp, top = 8.dp, end = 0.dp, bottom = 8.dp),
        ) {
            GlideImage(
                data = url,
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .align(CenterVertically),
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
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W900,
                    fontSize = 14.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun UserCardsHolder(data: List<PersonData>) {
    Card(shape = RoundedCornerShape(16.dp)){
        Column(
            modifier = Modifier.fillMaxWidth().background(color = BackgroundInputColor)
        ) {
            data.forEach { person ->
                UserNameAndAvatar(person.name, person.avatarUrl)
            }
        }
    }
}

@Composable
fun UserCardsHolderWithTitle(@StringRes titleTextId: Int, data: List<PersonData>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            stringResource(titleTextId),
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.subtitle2,
        )
        UserCardsHolder(data)
    }
}


data class PersonData(
    val name: String,
    val avatarUrl: String,
)

