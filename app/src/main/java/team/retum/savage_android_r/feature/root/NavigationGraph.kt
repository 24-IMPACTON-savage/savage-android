package team.retum.savage_android_r.feature.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import team.retum.savage_android_r.application.SavageApp
import team.retum.savage_android_r.feature.findworker.FindWorkerScreen
import team.retum.savage_android_r.feature.matchingworker.MatchingWorkerScreen
import team.retum.savage_android_r.feature.membership.MembershipScreen
import team.retum.savage_android_r.feature.onboarding.StartScreen
import team.retum.savage_android_r.feature.onboarding.join.Join1Screen
import team.retum.savage_android_r.feature.onboarding.join.Join2Screen
import team.retum.savage_android_r.feature.onboarding.login.Login1Screen
import team.retum.savage_android_r.feature.onboarding.login.Login2Screen
import team.retum.savage_android_r.feature.post.PostScreen
import team.retum.savage_android_r.feature.postpay.PostPayScreen
import team.retum.savage_android_r.feature.posttime.PostTimeScreen
import team.retum.savage_android_r.feature.setting.SettingScreen


@Composable
fun NavigationGraph(
    navController: NavHostController,
) {

    val enableAutoLogin = SavageApp.prefs.accessToken.isNotBlank()

    NavHost(
        navController = navController,
        startDestination = getStartDestination(enableAutoLogin = enableAutoLogin),
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
            FindWorkerScreen(navController = navController)
        }
        composable(NavGroup.Onboarding.Login2.id + "/{name}") {
            val name = it.arguments?.getString("name")
            Login2Screen(navController = navController, name = name ?: "")
        }
        composable(NavGroup.Onboarding.Login1.id) {
            Login1Screen(navController = navController)
        }
        composable(NavGroup.Main.Post.id) {
            PostScreen(navController = navController)
        }
        composable(NavGroup.Main.PostTime.id) {
            PostTimeScreen(navController = navController)
        }
        composable(NavGroup.Main.PostPay.id) {
            PostPayScreen(navController = navController)
        }
        composable(NavGroup.Main.MatchingWorker.id) {
            MatchingWorkerScreen(navController = navController)
        }
        composable(NavGroup.Main.Setting.id) {
            SettingScreen(navController = navController)
        }
        composable(NavGroup.Main.Membership.id) {
            //MembershipScreen(navController = navController)
        }
    }
}

fun getStartDestination(enableAutoLogin: Boolean) =
    if (enableAutoLogin) NavGroup.Main.Map.id else NavGroup.Onboarding.Start.id
