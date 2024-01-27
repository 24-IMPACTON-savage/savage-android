package team.retum.savage_android_r

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import team.retum.savage_android_r.application.PreferenceManager
import team.retum.savage_android_r.application.SavageApp
import team.retum.savage_android_r.feature.root.NavigationGraph
import team.retum.savage_android_r.ui.theme.SavageandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val context = LocalContext.current

            SavageApp.prefs = PreferenceManager(context)

            val navController = rememberNavController()
            SavageandroidTheme {
                NavigationGraph(navController = navController)
            }
        }
    }
}
