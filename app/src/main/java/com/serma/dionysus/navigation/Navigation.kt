package com.serma.dionysus.navigation

import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import com.serma.dionysus.navigation.Destinations.Event
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
    const val Event = "event/{eventId}"
    const val Profile = "profile"
    const val Logout = "logout"
}

class Action(navController: NavController) {
    val login: () -> Unit = { navController.navigate(Login) }
    val splash: () -> Unit = { navController.navigate(Splash) }
    val events: () -> Unit = { navController.navigate(Events) }
    val event: (String) -> Unit =
        { id -> navController.navigate(Event.replace("{eventId}", id)) }
    val registration: () -> Unit = { navController.navigate(Registration) }
    val profile: () -> Unit = { navController.navigate(Profile) }
    val logout: () -> Unit = {
        navController.navigate(Login) {
            popUpTo(Login) {}
        }
    }
}