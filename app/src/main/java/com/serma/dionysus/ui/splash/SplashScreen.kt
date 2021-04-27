package com.serma.dionysus.ui.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.serma.dionysus.common.ui.BrandLogo

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen({},{})
}

@Composable
fun SplashScreen(
    navigateForward: () -> Unit,
    navigateRegistration: () -> Unit,
    viewModel: SplashScreenViewModel = viewModel()
) {
    val splashState = viewModel.uiState.collectAsState()
    BrandLogo(Modifier.fillMaxSize())
    when (splashState.value.isAuth) {
        AuthStateSplash.AUTH -> navigateForward()
        AuthStateSplash.NOT_AUTH -> navigateRegistration()
        else -> return
    }
}