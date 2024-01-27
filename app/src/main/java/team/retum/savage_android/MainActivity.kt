package team.retum.savage_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import team.retum.savage_android.feature.root.NavigationGraph
import team.retum.savage_android.ui.theme.SavageandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            SavageandroidTheme {
                NavigationGraph(navController = navController)
            }
        }
    }
}
