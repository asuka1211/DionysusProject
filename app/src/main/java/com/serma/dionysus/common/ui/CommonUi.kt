package com.serma.dionysus.common.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import com.google.accompanist.glide.GlideImage
import com.serma.dionysus.R

@Preview
@Composable
fun PreviewCommon() {
    PersonItem(
        "https://static7.depositphotos.com/1314241/789/i/600/depositphotos_7890698-stock-photo-ferocious-lion.jpg",
        "лев ебать"
    )
    BrandLogo()
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
            contentDescription = null,
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
fun BrandLogo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .wrapContentHeight(CenterVertically)
    ) {
        CommonImage(
            R.drawable.auth_ic_logo,
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxHeight()
        )
    }
}

@Composable
fun CommonTopAppBar(
    openProfile: () -> Unit,
    logout: () -> Unit,
    navigateBack: (() -> Unit)? = null,
) {
    val expanded = remember { mutableStateOf(false) }
    TopAppBar(
        title = {},
        navigationIcon = {
            navigateBack?.let {
                IconButton(onClick = it) {
                    Icon(Icons.Rounded.ArrowBack, null)
                }
            }
        },
        actions = {
            IconButton(onClick = { expanded.value = true }) {
                Icon(
                    Icons.Filled.MoreVert,
                    null
                )
            }

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = true }
            ) {
                DropdownMenuItem(onClick = openProfile) {
                    Text(stringResource(id = R.string.profile))
                }
                DropdownMenuItem(onClick = logout) {
                    Text(stringResource(id = R.string.logout))
                }
            }
        }
    )
}