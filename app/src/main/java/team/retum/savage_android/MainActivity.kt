package team.retum.savage_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.softpie.karabiner.ui.root.NavigationGraph
import team.retum.savage_android.ui.component.SavageButton
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
