package com.serma.dionysus.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import com.google.accompanist.glide.GlideImage
import com.serma.dionysus.R
import com.serma.dionysus.common.ui.CommonGradientButton
import com.serma.dionysus.common.ui.CommonTextFieldWithTitle
import com.serma.dionysus.common.ui.SpacerRow
import com.serma.dionysus.common.theme.BackgroundColor
import com.serma.dionysus.common.theme.DionysusTheme

private val color = Brush.horizontalGradient(listOf(Color(0xFF432DD4), Color(0xFF7180B9)))

@Composable
@Preview
fun ProfileScreenPreview() {
    ProfileScreen(
        ProfileData(
            "https://lh3.googleusercontent.com/proxy/XtFXriM2QoI-FZaFGc_pwO13_TbmBUl0d4ZTcSmyTMTpnjglEDFSgru8qoI0oJqmEmfKNIYiwCXsTKxp3Ns90T1rL1E",
            "Тодд Говард",
            "12-11-1999",
            "Бесезда",
            "Маями",
            "Люблю продавать скайрим "
        )
    )
}

@Composable
fun ProfileScreen(data: ProfileData) {
    DionysusTheme {
        Surface(modifier = Modifier.background(BackgroundColor)) {
            val state = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(state)
            ) {
                SpacerRow(16)
                GlideImage(
                    data = data.image,
                    modifier = Modifier
                        .height(160.dp)
                        .width(160.dp)
                        .align(Alignment.CenterHorizontally),
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
                CommonTextFieldWithTitle(
                    titleTextId = R.string.profile_name,
                    hintTextId = R.string.profile_name_hint,
                    onValueChange = {})
                CommonTextFieldWithTitle(
                    titleTextId = R.string.profile_date,
                    hintTextId = R.string.profile_date_hint,
                    onValueChange = {})
                CommonTextFieldWithTitle(
                    titleTextId = R.string.profile_work,
                    hintTextId = R.string.profile_work_hint,
                    onValueChange = {})
                CommonTextFieldWithTitle(
                    titleTextId = R.string.profile_address,
                    hintTextId = R.string.profile_address_hint,
                    onValueChange = {})
                CommonTextFieldWithTitle(
                    titleTextId = R.string.profile_about,
                    hintTextId = R.string.profile_about_hint,
                    onValueChange = {})
                SpacerRow(24)
                CommonGradientButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    textId = R.string.save,
                    gradient = color,
                    onClick = {})
                SpacerRow(16)
            }
        }
    }
}

data class ProfileData(
    val image: String,
    val name: String,
    val birthday: String,
    val work: String,
    val address: String,
    val about: String
)