package team.retum.savage_android.feature.matchingworker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import team.retum.savage_android.R
import team.retum.savage_android.ui.component.SavageAppBar
import team.retum.savage_android.ui.component.SavageButton
import team.retum.savage_android.ui.theme.SavageColor
import team.retum.savage_android.ui.theme.SavageTypography

@Composable
internal fun MatchingWorkerScreen(
    navController: NavController
) {
    Column {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .paint(painter = painterResource(id = R.drawable.bg_matching_worker))
        ) {
            SavageAppBar(
                modifier = Modifier.padding(top = 8.dp),
                color = SavageColor.Transparent,
                callback = {
                    navController.popBackStack()
                },
                content = {},
                tint = SavageColor.White,
            )
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "안녕하세요! \uD83D\uDC4B",
                    color = SavageColor.White,
                    style = SavageTypography.HeadLine1,
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "로버트 다우니 주니어 외 3명",
                    color = SavageColor.White,
                    style = SavageTypography.Body2,
                )
                Text(
                    modifier = Modifier.padding(top = 32.dp),
                    text = "편하게 이지라고 불러주세요!",
                    color = SavageColor.White,
                    style = SavageTypography.Body2,
                )
            }
        }
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                modifier = Modifier.padding(top = 28.dp),
                text = "시간대",
                style = SavageTypography.Body1,
                color = SavageColor.Gray60,
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "15:00 ~ 18:00",
                style = SavageTypography.HeadLine1,
            )
            Text(
                modifier = Modifier.padding(top = 32.dp),
                text = "입금 정보",
                style = SavageTypography.Body1,
                color = SavageColor.Gray60,
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "시간 당 / 50000원",
                style = SavageTypography.HeadLine1,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "무료 매칭이 4번 남았어요!",
            color = SavageColor.Gray40,
            style = SavageTypography.Body1,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 14.dp),
            text = "지금 바로 구독하기",
            style = SavageTypography.Body2,
            color = SavageColor.Primary40,
        )
        Spacer(modifier = Modifier.height(8.dp))
        SavageButton(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "전화 걸기",
            isAbleClick = true,
            onClick = {},
        )
        Spacer(modifier = Modifier.padding(bottom = 24.dp))

    }
}