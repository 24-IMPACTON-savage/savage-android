package team.retum.savage_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import team.retum.savage_android.application.PreferenceManager
import team.retum.savage_android.application.SavageApp
import team.retum.savage_android.feature.root.NavigationGraph
import team.retum.savage_android.ui.theme.SavageandroidTheme

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
