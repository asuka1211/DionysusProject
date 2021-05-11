package com.serma.dionysus

import androidx.compose.foundation.background
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.compose.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.serma.dionysus.ui.auth.registration.RegistrationScreen
import com.serma.dionysus.auth.manager.SessionManager
import com.serma.dionysus.common.theme.BackgroundColor
import com.serma.dionysus.common.theme.DionysusTheme
import com.serma.dionysus.navigation.Action
import com.serma.dionysus.navigation.Destinations.Event
import com.serma.dionysus.navigation.Destinations.Events
import com.serma.dionysus.navigation.Destinations.Graph
import com.serma.dionysus.navigation.Destinations.Login
import com.serma.dionysus.navigation.Destinations.Profile
import com.serma.dionysus.navigation.Destinations.Registration
import com.serma.dionysus.navigation.Destinations.Splash
import com.serma.dionysus.navigation.Destinations.Tasks
import com.serma.dionysus.ui.auth.login.LoginScreen
import com.serma.dionysus.ui.eventinfo.EventInfoScreen
import com.serma.dionysus.ui.events.EventsScreen
import com.serma.dionysus.ui.graph.GraphScreen
import com.serma.dionysus.ui.profile.ProfileScreen
import com.serma.dionysus.ui.splash.SplashScreen
import com.serma.dionysus.ui.tasklist.pager.TaskPager

@ExperimentalMaterialApi
@ExperimentalStdlibApi
@ExperimentalPagerApi
@Composable
fun DionysusComposeApp(openDatePicker: OpenDatePicker, sessionManager: SessionManager) {
    val navController = rememberNavController()
    fun logout(){
        navController.navigate(Login) {
            popUpTo = 0
        }
        sessionManager.logout()
    }
    val actions = remember(navController) { Action(navController) }
    DionysusTheme {
        Surface(modifier = Modifier.background(BackgroundColor)) {
            NavHost(
                navController = navController,
                startDestination = Splash
            ) {
                composable(Splash) {
                    SplashScreen(
                        navigateForward = {
                            navController.navigate(Events) {
                                popUpTo = 0
                            }
                        },
                        navigateRegistration = actions.registration,
                        viewModel = hiltNavGraphViewModel(it)
                    )
                }
                composable(Login) {
                    LoginScreen(
                        navigateSuccess = {
                            navController.navigate(Events) {
                                popUpTo = 0
                            }
                        },
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
                        openEvent = { id -> navController.navigate("event/$id") },
                        logout = { logout() },
                        viewModel = hiltNavGraphViewModel(it)
                    )
                }
                composable(
                    Graph,
                    arguments = listOf(navArgument("eventId") { defaultValue = "" })
                ) {
                    GraphScreen(
                        openProfile = actions.profile,
                        navigateBack = { navController.popBackStack() },
                        logout = { logout() },
                        viewModel = hiltNavGraphViewModel(it)
                    )
                }
                composable(
                    Event,
                    arguments = listOf(navArgument("eventId") { defaultValue = "" })
                ) {
                    EventInfoScreen(
                        eventId = requireNotNull(it.arguments?.getString("eventId")),
                        openProfile = actions.profile,
                        openGraph = { id -> navController.navigate("graph/$id") },
                        navigateBack = { navController.popBackStack() },
                        logout = { logout() },
                        viewModel = hiltNavGraphViewModel(it),
                        openTasks = { id -> navController.navigate("tasks/$id") },
                    )
                }
                composable(Profile) {
                    ProfileScreen(
                        openDatePicker = openDatePicker,
                        viewModel = hiltNavGraphViewModel(it),
                        logout = { logout() },
                        navigateBack = { navController.popBackStack() },
                    )
                }
                composable(Tasks,
                    arguments = listOf(navArgument("eventId") { defaultValue = "" })) {
                    TaskPager(
                        eventId = requireNotNull(it.arguments?.getString("eventId")),
                        viewModel = hiltNavGraphViewModel(it),
                        tasksViewModel = hiltNavGraphViewModel(it),
                        logout = { logout() },
                        navigateBack = { navController.popBackStack() },
                        openProfile = actions.profile,
                        openTask = {}
                    )
                }
            }
        }
    }
}