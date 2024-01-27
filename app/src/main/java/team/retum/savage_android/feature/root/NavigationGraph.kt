package team.retum.savage_android.feature.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import team.retum.savage_android.feature.onboarding.Join1Screen
import team.retum.savage_android.feature.onboarding.StartScreen


@Composable
fun NavigationGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = getStartDestination()
    ) {
        composable(NavGroup.Onboarding.Start.id) {
            StartScreen(navController = navController)
        }
        composable(NavGroup.Onboarding.Join1.id) {
            Join1Screen(navController = navController)
        }

    }
}

private val Start = NavGroup.Onboarding.Start

fun getStartDestination() =
    /*if (enableAutoLogin) NavGroup.Main.MAIN.id else */Start.id
