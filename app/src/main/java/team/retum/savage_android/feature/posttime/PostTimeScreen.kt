package team.retum.savage_android.feature.posttime

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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import team.retum.savage_android.ui.component.SavageAppBar
import team.retum.savage_android.ui.component.SavageButton
import team.retum.savage_android.ui.theme.SavageColor
import team.retum.savage_android.ui.theme.SavageTypography
import team.retum.savage_android.ui.theme.savageClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostTimeScreen(navController: NavController) {

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
                    text = "시간을 설정해 주세요.",
                    style = SavageTypography.HeadLine1
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, top = 64.dp),
                text = "시작하는 시간",
                style = SavageTypography.Body2
            )

            var startHour by remember { mutableIntStateOf(0) }
            var startMinutes by remember { mutableIntStateOf(0) }
            var showStartDialog by remember { mutableStateOf(false) }
            val timeState = rememberTimePickerState(
                initialHour = startHour,
                initialMinute = startMinutes
            )

            if (showStartDialog) {
                AlertDialog(
                    onDismissRequest = { showStartDialog = false },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(color = Color.White)
                            .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TimePicker(
                            state = timeState,
                            colors = TimePickerDefaults.colors(
                                timeSelectorSelectedContainerColor = Color.White,
                                timeSelectorUnselectedContainerColor = Color.White,
                                clockDialSelectedContentColor = Color.White,
                                clockDialUnselectedContentColor = SavageColor.Primary40,
                                periodSelectorSelectedContainerColor = Color.White,
                                selectorColor = SavageColor.Primary40,
                                clockDialColor = SavageColor.Gray10
                            )
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                style = SavageTypography.Body2,
                                text = "닫기",
                                color = SavageColor.Primary40,
                                modifier = Modifier
                                    .savageClickable(
                                        rippleEnable = false,
                                    ) {
                                        showStartDialog = false
                                    }
                                    .padding(end = 16.dp)
                            )
                            SavageButton(
                                modifier = Modifier
                                    .width(84.dp),
                                onClick = {
                                    showStartDialog = false
                                    startHour = timeState.hour
                                    startMinutes = timeState.minute
                                },
                                isAbleClick = true,
                                text = "완료",
                            )
                        }
                    }
                }
            }

            Text(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp)
                    .savageClickable(
                        rippleEnable = false
                    ) {
                        showStartDialog = true
                    },
                text = String.format("${if (startHour < 12) "오전" else  "오후"} %02d:%02d", startHour, startMinutes),
                style = SavageTypography.HeadLine1
            )

            Text(
                modifier = Modifier
                    .padding(start = 16.dp, top = 80.dp),
                text = "끝나는 시간",
                style = SavageTypography.Body2
            )
            var endHour by remember { mutableIntStateOf(0) }
            var endMinutes by remember { mutableIntStateOf(0) }
            var showEndDialog by remember { mutableStateOf(false) }
            val timeState2 = rememberTimePickerState(
                initialHour = endHour,
                initialMinute = endMinutes
            )

            if (showEndDialog) {
                AlertDialog(
                    onDismissRequest = { showEndDialog = false },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(color = Color.White)
                            .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TimePicker(
                            state = timeState2,
                            colors = TimePickerDefaults.colors(
                                timeSelectorSelectedContainerColor = Color.White,
                                timeSelectorUnselectedContainerColor = Color.White,
                                clockDialSelectedContentColor = Color.White,
                                clockDialUnselectedContentColor = SavageColor.Primary40,
                                periodSelectorSelectedContainerColor = Color.White,
                                selectorColor = SavageColor.Primary40,
                                clockDialColor = SavageColor.Gray10
                            )
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                style = SavageTypography.Body2,
                                text = "닫기",
                                color = SavageColor.Primary40,
                                modifier = Modifier
                                    .savageClickable(
                                        rippleEnable = false,
                                    ) {
                                        showEndDialog = false
                                    }
                                    .padding(end = 16.dp)
                            )
                            SavageButton(
                                modifier = Modifier
                                    .width(84.dp),
                                onClick = {
                                    showEndDialog = false
                                    endHour = timeState2.hour
                                    endMinutes = timeState2.minute
                                },
                                isAbleClick = true,
                                text = "완료",
                            )
                        }
                    }
                }
            }

            Text(
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp)
                    .savageClickable(
                        rippleEnable = false
                    ) {
                        showEndDialog = true
                    },
                text = String.format("${if (endHour < 12) "오전" else "오후"} %02d:%02d", endHour, endMinutes),
                style = SavageTypography.HeadLine1
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
