package com.serma.auth.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serma.auth.R
import com.serma.auth.ui.base.AuthTextPair
import com.serma.common_ui.common.CommonGradientButton
import com.serma.common_ui.common.CommonImage
import com.serma.common_ui.common.CommonTextFieldWithTitle
import com.serma.common_ui.common.SpacerRow
import com.serma.common_ui.theme.BackgroundColor
import com.serma.common_ui.theme.DionysusTheme

private val color = Brush.horizontalGradient(listOf(Color(0xFF432DD4), Color(0xFF7180B9)))

@Preview
@Composable
fun LoginScreenPreview() {
    DionysusTheme {
        LoginScreen()
    }
}

@Composable
fun LoginScreen() {
    Surface(modifier = Modifier.background(BackgroundColor)) {
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
                    hintTextId = R.string.auth_login_hint_login,
                    onValueChange = {})
                CommonTextFieldWithTitle(
                    titleTextId = R.string.auth_login,
                    hintTextId = R.string.auth_login_hint_password,
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
                    R.string.auth_login_need_account,
                    R.string.auth_login_registration,
                    {})
            }
        }
    }
}

@Composable
fun BrandLogo(modifier: Modifier) {
    Column(
        modifier = modifier
            .wrapContentHeight(CenterVertically)
    ) {
        CommonImage(
            Modifier
                .align(CenterHorizontally)
                .fillMaxHeight(),
            R.drawable.auth_ic_logo,
        )
    }
}
