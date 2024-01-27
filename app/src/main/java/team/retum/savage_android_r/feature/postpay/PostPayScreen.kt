package team.retum.savage_android_r.feature.postpay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import team.retum.savage_android_r.ui.component.SavageAppBar
import team.retum.savage_android_r.ui.component.SavageButton
import team.retum.savage_android_r.ui.component.SavageTextField
import team.retum.savage_android_r.ui.theme.SavageColor
import team.retum.savage_android_r.ui.theme.SavageTypography

@Composable
fun PostPayScreen(
    navController: NavController
) {

    var pay by remember {
        mutableStateOf(0)
    }

    SavageAppBar(
        callback = {
            navController.popBackStack()
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
            ) {
                Text(
                    text = "임금 기준을 설정해 주세요.",
                    style = SavageTypography.HeadLine1
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            val radioOptions = listOf("건당","시급","일당","주급", "월급")
            var selectedOption by remember { mutableStateOf(radioOptions[0]) }

            Row(
                modifier = Modifier.padding(8.dp),
            ) {
                radioOptions.forEach { fruitName ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = (fruitName == selectedOption),
                            onClick = { selectedOption = fruitName },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = SavageColor.Primary40
                            )
                        )
                        Text(
                            text = fruitName,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
            SavageTextField(
                modifier = Modifier.padding(horizontal = 20.dp),
                value = pay.toString(), hint = "월급으로 얼마를 주실 건가요?", onValueChange = {
                    if (it.isDigitsOnly()) {
                        pay = it.toInt() }
                    }
            )

            Spacer(modifier = Modifier.weight(1f))
            SavageButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = {
                    val total = "$pay/$selectedOption"

                    // 현재 화면의 NavController를 이용하여 이전 화면의 NavBackStackEntry를 가져옴
                    val previousBackStackEntry = navController.previousBackStackEntry
                    previousBackStackEntry?.arguments?.putString("total", total)

                    // 현재 화면을 popBackStack
                    navController.popBackStack()
                },
                text = "완료",
                isAbleClick = true,
            )
            Spacer(modifier = Modifier.padding(bottom = 24.dp))
        }
    }
}