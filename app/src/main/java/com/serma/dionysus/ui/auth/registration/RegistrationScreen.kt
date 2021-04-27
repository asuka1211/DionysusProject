package com.serma.auth.ui.registration

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.serma.auth.ui.base.AuthTextPair
import com.serma.dionysus.common.theme.DionysusTheme
import com.serma.dionysus.R
import com.serma.dionysus.common.ui.*
import com.serma.dionysus.ui.auth.login.LoginViewModel

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
    viewModel: LoginViewModel = viewModel()
) {
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
                onValueChange = {})
            CommonTextFieldWithTitle(
                titleTextId = R.string.auth_email,
                hintTextId = R.string.auth_hint_email,
                onValueChange = {}
            )
            CommonTextFieldWithTitle(
                titleTextId = R.string.auth_login,
                hintTextId = R.string.auth_hint_password,
                onValueChange = {},
                forPassword = true
            )
            SpacerRow(24)
            CommonGradientButton(
                gradient = color,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                textId = R.string.auth_login_btn,
                onClick = {}
            )
            AuthTextPair(
                R.string.auth_login_have_account,
                R.string.auth_login_btn,
                {})
        }
    }
}
