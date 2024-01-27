package team.retum.savage_android.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import team.retum.savage_android.R
import team.retum.savage_android.ui.component.SavageButton
import team.retum.savage_android.ui.theme.SavageTypography

@Composable
fun StartScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(top = 72.dp),
            text = "건강한 노동을 시작해 봐요!",
            style = SavageTypography.HeadLine1
        )
        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier
                .scale(0.75f),
            painter = painterResource(R.drawable.image_simple_and_fast),
            contentDescription = null
        )

        // join button
        // login button
        SavageButton(
            onClick = {

            },
            )
    }

}