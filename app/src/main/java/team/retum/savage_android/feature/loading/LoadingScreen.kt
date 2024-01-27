package team.retum.savage_android.feature.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import team.retum.savage_android.ui.theme.SavageColor
import team.retum.savage_android.ui.theme.SavageTypography

@Composable
internal fun LoadingScreen(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "매칭중",
            color = SavageColor.Primary40,
            style = SavageTypography.Body2,
        )
    }
}