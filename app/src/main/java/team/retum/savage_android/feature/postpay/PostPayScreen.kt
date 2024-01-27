package team.retum.savage_android.feature.postpay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import team.retum.savage_android.ui.component.SavageAppBar
import team.retum.savage_android.ui.component.SavageButton
import team.retum.savage_android.ui.component.SavageTextField
import team.retum.savage_android.ui.theme.SavageColor
import team.retum.savage_android.ui.theme.SavageTypography
import team.retum.savage_android.ui.theme.savageClickable

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
                    // handling
                    navController.popBackStack()
                },
                text = "완료",
                isAbleClick = true,
            )
            Spacer(modifier = Modifier.padding(bottom = 24.dp))
        }
    }
}