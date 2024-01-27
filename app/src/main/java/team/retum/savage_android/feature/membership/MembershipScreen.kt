package team.retum.savage_android.feature.membership

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import team.retum.savage_android.R
import team.retum.savage_android.ui.component.SavageAppBar
import team.retum.savage_android.ui.component.SavageButton
import team.retum.savage_android.ui.theme.SavageColor
import team.retum.savage_android.ui.theme.SavageTypography

@Composable
internal fun MembershipScreen(navController: NavController) {
    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.bg_membership),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
        )
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            SavageAppBar(
                callback = {
                           navController.popBackStack()
                           },
                color = SavageColor.Transparent,
                tint = SavageColor.White,
            ) {

            }
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "정기 멤버쉽 혜택 \uD83D\uDD25",
                    style = SavageTypography.HeadLine3,
                    color = SavageColor.White,
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "정기 멤버쉽에 가입하고 다양한 혜택을 맞이해 보세요!!",
                    style = SavageTypography.Body2,
                    color = SavageColor.White,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "한 달간 매칭 무제한 무료 서비스",
                    style = SavageTypography.Body2,
                    color = SavageColor.White,
                )
                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "성과 높은 노동자 매칭 증가",
                    style = SavageTypography.Body2,
                    color = SavageColor.White,
                )
                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "광고 제거",
                    style = SavageTypography.Body2,
                    color = SavageColor.White,
                )
                Text(
                    modifier = Modifier.padding(bottom = 40.dp),
                    text = "장소 15개 지정 가능",
                    style = SavageTypography.Body2,
                    color = SavageColor.White,
                )
                SavageButton(
                    modifier = Modifier.padding(bottom = 24.dp),
                    text = "단 돈 5,900원",
                    onClick = {},
                    isAbleClick = true,
                )
            }
        }
    }
}