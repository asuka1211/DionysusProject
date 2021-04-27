package com.serma.dionysus

import androidx.compose.foundation.background
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.serma.auth.ui.registration.RegistrationScreen
import com.serma.dionysus.common.theme.BackgroundColor
import com.serma.dionysus.common.theme.DionysusTheme
import com.serma.dionysus.navigation.Action
import com.serma.dionysus.navigation.Destinations.Events
import com.serma.dionysus.navigation.Destinations.Login
import com.serma.dionysus.navigation.Destinations.Registration
import com.serma.dionysus.navigation.Destinations.Splash
import com.serma.dionysus.ui.auth.login.LoginScreen
import com.serma.dionysus.ui.events.EventsScreen
import com.serma.dionysus.ui.splash.SplashScreen

@Composable
fun DionysusComposeApp() {
    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }
    DionysusTheme {
        Surface(modifier = Modifier.background(BackgroundColor)) {
            NavHost(
                navController = navController,
                startDestination = Events
            ) {
                composable(Splash) {
                    SplashScreen(
                        navigateForward = actions.events,
                        navigateRegistration = actions.registration,
                        viewModel = hiltNavGraphViewModel(it)
                    )
                }
                composable(Login) {
                    LoginScreen(
                        navigateSuccess = actions.events,
                        navigateRegistration = actions.registration,
                        loginViewModel = hiltNavGraphViewModel(it)
                    )
                }
                composable(Registration) {
                    RegistrationScreen(
                        navigateSuccess = actions.events,
                        navigateLogin = actions.login,
                        viewModel = hiltNavGraphViewModel(it)
                    )
                }
                composable(Events) {
                    EventsScreen(
                        openProfile = actions.profile,
                        openEvent = {  },
                        logout = {},
                        eventsViewModel = hiltNavGraphViewModel(it)
                    )
                }
            }
        }
    }
}