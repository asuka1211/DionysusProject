package com.serma.dionysus

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val iconId: Int
) {
    object Events : BottomNavigationScreens(
        "Events",
        R.string.screen_route_events,
        R.drawable.ic_event
    )

    object Stats : BottomNavigationScreens(
        "Stats",
        R.string.screen_route_stats,
        R.drawable.ic_stats
    )

    object Tasks : BottomNavigationScreens(
        "Tasks",
        R.string.screen_route_tasks,
        R.drawable.ic_task
    )
}

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val bottomNavigationItems = listOf<BottomNavigationScreens>()
    Scaffold(
        bottomBar = {
            SpookyAppBottomNavigation(navController, bottomNavigationItems)
        },
    ) {
        MainScreenNavigationConfigurations(navController)
    }
}

@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController
) {
    NavHost(navController, startDestination = BottomNavigationScreens.Events.route) {
        composable(BottomNavigationScreens.Events.route) {
            //ScaryScreen(ScaryAnimation.Frankendroid)
        }
        composable(BottomNavigationScreens.Stats.route) {
            // ScaryScreen(ScaryAnimation.Pumpkin)
        }
        composable(BottomNavigationScreens.Tasks.route) {
            // ScaryScreen(ScaryAnimation.Ghost)
        }
    }
}

@Composable
private fun SpookyAppBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationScreens>
) {
    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = screen.iconId), null) },
                label = { Text(stringResource(id = screen.resourceId)) },
                selected = currentRoute == screen.route,
                onClick = {
                    // This if check gives us a "singleTop" behavior where we do not create a
                    // second instance of the composable if we are already on that destination
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}