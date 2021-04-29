package com.serma.dionysus.navigation

import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.serma.dionysus.navigation.Destinations.Events
import com.serma.dionysus.navigation.Destinations.Login
import com.serma.dionysus.navigation.Destinations.Profile
import com.serma.dionysus.navigation.Destinations.Registration
import com.serma.dionysus.navigation.Destinations.Splash

object Destinations {
    const val Splash = "splash"
    const val Login = "login"
    const val Registration = "registration"
    const val Events = "events"
    const val Event = "events/{eventId}"
    const val Profile = "profile"
}

class Action(navController: NavHostController){
    val login: () -> Unit = { navController.navigate(Login) }
    val splash: () -> Unit = { navController.navigate(Splash) }
    val events: () -> Unit = { navController.navigate(Events) }
    val registration: () -> Unit = { navController.navigate(Registration) }
    val profile: () -> Unit = { navController.navigate(Profile) }
    val navigateBack: () -> Unit = { navController.popBackStack() }
}