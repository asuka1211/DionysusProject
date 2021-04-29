package com.serma.dionysus.ui.profile

import androidx.activity.compose.registerForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import com.google.accompanist.glide.GlideImage
import com.serma.dionysus.OpenDatePicker
import com.serma.dionysus.R
import com.serma.dionysus.common.ui.*
import java.text.SimpleDateFormat
import java.util.*

private val color = Brush.horizontalGradient(listOf(Color(0xFF432DD4), Color(0xFF7180B9)))

@Composable
@Preview
fun ProfileScreenPreview() {
    //   ProfileScreen({}
//        ProfileData(
//            "https://lh3.googleusercontent.com/proxy/XtFXriM2QoI-FZaFGc_pwO13_TbmBUl0d4ZTcSmyTMTpnjglEDFSgru8qoI0oJqmEmfKNIYiwCXsTKxp3Ns90T1rL1E",
//            "Тодд Говард",
//            "12-11-1999",
//            "Бесезда",
//        )
//    )
}

@Composable
fun ProfileScreen(openDatePicker: OpenDatePicker, viewModel: ProfileViewModel) {

    val scrollState = rememberScrollState()
    val uiState = viewModel.uiState.collectAsState()
    val nameState = remember { mutableStateOf(uiState.value.data?.name ?: "") }
    val dateState = remember { mutableStateOf(uiState.value.data?.birthday ?: "") }
    val imageState = remember { mutableStateOf(uiState.value.data?.image ?: "") }
    val aboutState = remember { mutableStateOf(uiState.value.data?.about ?: "") }

    val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        imageState.value = it.toString()
    }
    val calendar = Calendar.getInstance()
    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    CommonBaseStateScreen(
        state = uiState,
        reload = {
            viewModel.reload()
        }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState)
        ) {
            SpacerRow(16)
            val imageModifier = Modifier
                .height(160.dp)
                .width(160.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {
                    launcher.launch("image/*")
                }
            GlideImage(
                data = imageState.value,
                modifier = imageModifier,
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
                error = {
                    Image(
                        painter = painterResource(R.drawable.ic_account_placeholder),
                        modifier = imageModifier,
                        contentDescription = null
                    )
                },
                fadeIn = true
            )
            CommonTextFieldWithTitle(
                titleTextId = R.string.profile_name,
                hintTextId = R.string.profile_name_hint,
                onValueChange = { nameState.value = it },
                text = nameState.value
            )
            CommonTextWithTitleClickable(
                titleTextId = R.string.profile_date,
                hintTextId = R.string.profile_date_hint,
                text = dateState.value,
                onClick = {
                    openDatePicker.openDatePicker { date ->
                        calendar.timeInMillis = date
                        dateState.value = formatter.format(calendar.time)
                    }
                })
            CommonTextFieldWithTitle(
                titleTextId = R.string.profile_about,
                hintTextId = R.string.profile_about_hint,
                onValueChange = { aboutState.value = it },
                text = aboutState.value
            )
            SpacerRow(24)
            CommonGradientButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                textId = R.string.save,
                gradient = color,
                onClick = {
                    viewModel.update(
                        ProfileData(
                            imageState.value,
                            nameState.value,
                            dateState.value,
                            aboutState.value
                        )
                    )
                })
            SpacerRow(16)
        }
    }
}

data class ProfileData(
    val image: String,
    val name: String,
    val birthday: String,
    val about: String
)