package team.retum.savage_android.feature.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import team.retum.savage_android.application.SavageApp
import team.retum.savage_android.feature.complete.CompleteScreen
import team.retum.savage_android.feature.findworker.FindWorkerScreen
import team.retum.savage_android.feature.loading.LoadingScreen
import team.retum.savage_android.feature.matchingworker.MatchingWorkerScreen
import team.retum.savage_android.feature.onboarding.StartScreen
import team.retum.savage_android.feature.onboarding.join.Join1Screen
import team.retum.savage_android.feature.onboarding.join.Join2Screen
import team.retum.savage_android.feature.onboarding.login.Login1Screen
import team.retum.savage_android.feature.onboarding.login.Login2Screen
import team.retum.savage_android.feature.postpay.PostPayScreen
import team.retum.savage_android.feature.posttime.PostTimeScreen
import team.retum.savage_android.feature.setting.SettingScreen


@Composable
fun NavigationGraph(
    navController: NavHostController,
) {

    val enableAutoLogin = SavageApp.prefs.accessToken.isNotBlank()

    NavHost(
        navController = navController,
        startDestination = NavGroup.Main.Setting.id/*getStartDestination(enableAutoLogin = enableAutoLogin)*/,
    ) {
        composable(NavGroup.Onboarding.Start.id) {
            StartScreen(navController = navController)
        }
        composable(NavGroup.Onboarding.Join1.id) {
            Join1Screen(navController = navController)
        }
        composable(NavGroup.Onboarding.Join2.id + "/{name}") {
            val name = it.arguments?.getString("name")
            Join2Screen(navController = navController, name = name ?: "")
        }
        composable(NavGroup.Main.Map.id) {
            FindWorkerScreen()
        }
        composable(NavGroup.Onboarding.Login2.id + "/{name}") {
            val name = it.arguments?.getString("name")
            Login2Screen(navController = navController, name = name ?: "")
        }
        composable(NavGroup.Onboarding.Login1.id) {
            Login1Screen(navController = navController)
        }
        composable(NavGroup.Main.Loading.id) {
            LoadingScreen()
        }
        composable(NavGroup.Main.Complete.id) {
            CompleteScreen()
        }
        composable(NavGroup.Main.PostTime.id) {
            PostTimeScreen(navController = navController)
        }
        composable(NavGroup.Main.PostPay.id) {
            PostPayScreen(navController = navController)
        }
        composable(NavGroup.Main.MatchingWorker.id) {
            MatchingWorkerScreen()
        }
        composable(NavGroup.Main.Setting.id) {
            SettingScreen(navController = navController)
        }
    }
}

fun getStartDestination(enableAutoLogin: Boolean) =
    if (enableAutoLogin) NavGroup.Main.Map.id else NavGroup.Onboarding.Start.id
