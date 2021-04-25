package com.serma.dionysus.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.serma.auth.ui.base.AuthTextPair
import com.serma.dionysus.common.ui.CommonGradientButton
import com.serma.dionysus.common.ui.CommonImage
import com.serma.dionysus.common.ui.CommonTextFieldWithTitle
import com.serma.dionysus.common.ui.SpacerRow
import com.serma.dionysus.common.theme.BackgroundColor
import com.serma.dionysus.common.theme.DionysusTheme
import com.serma.dionysus.R
import com.serma.dionysus.ui.login.mvi.AuthState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach

private val color = Brush.horizontalGradient(listOf(Color(0xFF432DD4), Color(0xFF7180B9)))

@Preview
@Composable
fun LoginScreenPreview() {
    DionysusTheme {
        LoginScreen()
    }
}

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    val loginState = loginViewModel.uiState.collectAsState()
    Surface(modifier = Modifier.background(BackgroundColor)) {
        MainContent(loginViewModel, loginState.value.loading)
    }
}

@Composable
fun BrandLogo(modifier: Modifier) {
    Column(
        modifier = modifier
            .wrapContentHeight(CenterVertically)
    ) {
        CommonImage(
            R.drawable.auth_ic_logo,
            Modifier
                .align(CenterHorizontally)
                .fillMaxHeight()
        )
    }
}

@Composable
fun MainContent(loginViewModel: LoginViewModel, isLoading: Boolean) {
    val loginData = remember { mutableStateOf(LoginData("", "")) }

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
            CommonTextFieldWithTitle(
                titleTextId = R.string.auth_login,
                hintTextId = R.string.auth_hint_login,
                onValueChange = { loginData.value.username = it })
            CommonTextFieldWithTitle(
                titleTextId = R.string.auth_password,
                hintTextId = R.string.auth_hint_password,
                onValueChange = { loginData.value.password = it },
                forPassword = true
            )
            SpacerRow(24)
            if (isLoading) {
                CircularProgressIndicator(Modifier.align(CenterHorizontally))
            } else {
                CommonGradientButton(
                    gradient = color,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    textId = R.string.auth_login_btn,
                    onClick = {
                        loginData.value.apply {
                            loginViewModel.login(username, password)
                        }
                    }
                )
            }
            AuthTextPair(
                R.string.auth_login_need_account,
                R.string.auth_registration_btn,
                {})
        }

    }
}

data class LoginData(var username: String, var password: String)