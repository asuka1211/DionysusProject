package com.serma.dionysus.common.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.serma.dionysus.R
import com.serma.dionysus.common.mvi.BaseMviViewState
import com.serma.dionysus.common.theme.BackgroundColor
import com.serma.dionysus.common.theme.BackgroundInputColor
import com.serma.dionysus.common.theme.DionysusTheme

@Preview
@Composable
fun PreviewCommon() {
    DionysusTheme {

        val testData = PersonData(
            "Максим Яковлев",
            "https://s0.rbk.ru/v6_top_pics/media/img/5/46/756038770746465.jpg"
        )
        val listTestData = listOf(testData, testData)

        Column {
//            PersonItem(
//                "https://static7.depositphotos.com/1314241/789/i/600/depositphotos_7890698-stock-photo-ferocious-lion.jpg",
//                "лев ебать"
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            UserCard(
//                "лев ебать",
//                "https://static7.depositphotos.com/1314241/789/i/600/depositphotos_7890698-stock-photo-ferocious-lion.jpg"
//            )

            UserCardsHolderWithTitle(
                titleTextId = R.string.add_author,
                data = listTestData
            )

            AddingUserCardsWithTitle(
                titleTextId = R.string.add_author,
                buttonTextId = R.string.add_author,
                data = listTestData
            )
        }
    }
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
    logout: () -> Unit,
    openProfile: (() -> Unit)? = null,
    navigateBack: (() -> Unit)? = null,
) {
    val expanded = remember { mutableStateOf(false) }
    TopAppBar(
        title = {},
        navigationIcon = {
            navigateBack?.let {
                IconButton(onClick = { navigateBack() }) {
                    Icon(Icons.Rounded.ArrowBack, null)
                }
            }
        },
        actions = {
            IconButton(onClick = { expanded.value = true }) {
                Icon(Icons.Filled.MoreVert, null)
            }

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {
                openProfile?.let {
                    DropdownMenuItem(onClick = openProfile) {
                        Text(stringResource(id = R.string.profile))
                    }
                }

                DropdownMenuItem(onClick = logout) {
                    Text(stringResource(id = R.string.logout))
                }
            }
        }
    )
}

@Composable
fun UserCard(name: String, url: String) {
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
fun AddingTagWithTitle(
    @StringRes titleTextId: Int,
    @StringRes buttonTextId: Int,
    tags: List<String>
) {
    Column {
        Column {
            Text(
                stringResource(titleTextId),
                modifier = Modifier.padding(vertical = 8.dp),
                style = MaterialTheme.typography.subtitle2,
            )
            Card(shape = RoundedCornerShape(16.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = BackgroundInputColor)
                ) {
                    tags.forEach { tag ->
                        ReadOnlyTextField(tag)
                    }
                }
            }
        }
    }
}

@Composable
fun AddingUserCardsWithTitle(
    @StringRes titleTextId: Int,
    @StringRes buttonTextId: Int,
    data: List<PersonData>
) {
    Column {
        UserCardsHolderWithTitle(titleTextId, data)
        AddingButton(buttonTextId = buttonTextId, BackgroundInputColor,{})
    }
}

@Composable
fun UserCardWithTitle(@StringRes titleTextId: Int, person: PersonData) {
    Column {
        Text(
            stringResource(titleTextId),
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.subtitle2,
        )
        UserCard(person.name, person.avatarUrl)
    }
}


@Composable
fun UserCardsHolderWithTitle(@StringRes titleTextId: Int, data: List<PersonData>) {
    Column {
        Text(
            stringResource(titleTextId),
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.subtitle2,
        )
        Card(shape = RoundedCornerShape(16.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = BackgroundInputColor)
            ) {
                data.forEach { person ->
                    UserCard(person.name, person.avatarUrl)
                }
            }
        }
    }
}

@Composable
fun CommonErrorDialog(errorText: String, modifier: Modifier = Modifier, reload: () -> Unit) {
    Column(
        modifier = modifier
            .background(
                color = BackgroundColor,
                RoundedCornerShape(10.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = errorText,
            Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
        )
        OutlinedButton(onClick = reload, Modifier.padding(top = 16.dp)) {
            Text(
                text = stringResource(id = R.string.reload),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterVertically),
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}

@Composable
fun <T : BaseMviViewState> CommonBaseStateScreen(
    state: State<T>,
    reload: () -> Unit,
    content: @Composable () -> Unit
) {
    when {
        state.value.loading -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }
        state.value.error != null -> {
            Box(Modifier.fillMaxSize()) {
                CommonErrorDialog(
                    state.value.error?.message!!,
                    Modifier.align(Alignment.Center)
                ) {
                    reload()
                }
            }
        }
        else -> {
            content()
        }
    }
}

data class PersonData(
    val name: String,
    val avatarUrl
    : String,
)

