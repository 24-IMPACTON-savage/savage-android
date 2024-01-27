package team.retum.savage_android.feature.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import team.retum.savage_android.application.SavageApp
import team.retum.savage_android.feature.complete.CompleteScreen
import team.retum.savage_android.feature.findworker.FindWorkerScreen
import team.retum.savage_android.feature.loading.LoadingScreen
import team.retum.savage_android.feature.matchingworker.MatchingWorkerScreen
import team.retum.savage_android.feature.membership.MembershipScreen
import team.retum.savage_android.feature.onboarding.StartScreen
import team.retum.savage_android.feature.onboarding.join.Join1Screen
import team.retum.savage_android.feature.onboarding.join.Join2Screen
import team.retum.savage_android.feature.onboarding.login.Login1Screen
import team.retum.savage_android.feature.onboarding.login.Login2Screen
import team.retum.savage_android.feature.post.PostScreen
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
        composable(NavGroup.Main.Loading.id) {
            LoadingScreen(modifier = Modifier)
        }
        composable(NavGroup.Main.Complete.id) {
            CompleteScreen(modifier = Modifier)
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
        composable(NavGroup.Main.Map.id) {
            FindWorkerScreen(navController = navController)
        }
        composable(NavGroup.Main.Membership.id) {
            MembershipScreen()
        }
    }
}

fun getStartDestination(enableAutoLogin: Boolean) =
    if (enableAutoLogin) NavGroup.Main.Map.id else NavGroup.Onboarding.Start.id
