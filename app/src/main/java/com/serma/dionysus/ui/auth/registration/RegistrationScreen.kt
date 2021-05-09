package com.serma.dionysus.ui.auth.registration

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.serma.auth.ui.base.AuthTextPair
import com.serma.dionysus.R
import com.serma.dionysus.common.theme.DionysusTheme
import com.serma.dionysus.common.ui.BrandLogo
import com.serma.dionysus.common.ui.CommonGradientButton
import com.serma.dionysus.common.ui.CommonTextFieldWithTitle
import com.serma.dionysus.common.ui.SpacerRow
import com.serma.dionysus.ui.auth.base.AuthData
import com.serma.dionysus.ui.auth.base.AuthState
import kotlinx.coroutines.launch

private val color = Brush.horizontalGradient(listOf(Color(0xFF432DD4), Color(0xFF7180B9)))

@Preview
@Composable
fun LoginScreenPreview() {
    DionysusTheme {
        RegistrationScreen({}, {})
    }
}

@Composable
fun RegistrationScreen(
    navigateSuccess: () -> Unit,
    navigateLogin: () -> Unit,
    viewModel: RegistrationViewModel = viewModel()
) {

    val screenState = viewModel.uiState.collectAsState()
    val authData = remember {
        mutableStateOf(
            AuthData("", "")
        )
    }

    val scaffoldState = rememberScaffoldState()
    val snackbarCoroutineScope = rememberCoroutineScope()
    val incorrectDataString = stringResource(id = R.string.wrong_credentials)
    val fillFieldString = stringResource(id = R.string.empty_field)

    Scaffold(scaffoldState = scaffoldState) {
        when (screenState.value.authState) {
            AuthState.NONE -> {
            }
            AuthState.SUCCESS -> navigateSuccess()
            AuthState.WRONG_CREDENTIALS -> snackbarCoroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(incorrectDataString)
            }
        }
        if (screenState.value.error != null) {
            snackbarCoroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(screenState.value.error?.message ?: "")
            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            BrandLogo(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    .wrapContentHeight(align = Top)
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                CommonTextFieldWithTitle(titleTextId = R.string.auth_login,
                    hintTextId = R.string.auth_hint_login,
                    onValueChange = { authData.value.username = it })
                CommonTextFieldWithTitle(
                    titleTextId = R.string.auth_password,
                    hintTextId = R.string.auth_hint_password,
                    onValueChange = { authData.value.password = it }
                )
                SpacerRow(24)
                if (screenState.value.loading) {
                    CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
                } else {
                    CommonGradientButton(
                        gradient = color,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        textId = R.string.auth_registration_btn,
                        onClick = {
                            authData.value.apply {
                                if (username.isEmpty() || password.isEmpty()) {
                                    snackbarCoroutineScope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar(fillFieldString)
                                    }
                                } else {
                                    viewModel.registration(username, password)
                                }
                            }
                        }
                    )
                }
                AuthTextPair(
                    R.string.auth_login_have_account,
                    R.string.auth_login_btn
                ) { navigateLogin() }
            }
        }
    }
}