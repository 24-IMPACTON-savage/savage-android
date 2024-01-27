package team.retum.savage_android.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.retum.savage_android.ui.theme.SavageColor
import team.retum.savage_android.ui.theme.SavageTypography
import team.retum.savage_android.ui.theme.savageClickable

@Composable
fun SavageTextFieldWithEditBtn(
    modifier: Modifier = Modifier,
    value: String,
    editBtnValue: String = "변경",
    title: String = "전화번",
    titleTextStyle: TextStyle = SavageTypography.Body2,
    titleTextColor: Color = Color(0xFF676F7E),
    hint: String,
    subTitle: String = "",
    onValueChange: (String) -> Unit,
    isShowTitle: Boolean = true,
    isShowSubTitle: Boolean = true,
    onclick: () -> Unit = {},
    isTextField: Boolean = true,
) {
    val focusManager = LocalFocusManager.current
    Column(modifier = modifier.savageClickable {
        if (isTextField) {
            focusManager.moveFocus(FocusDirection.Next)
        } else {
            onclick()
        }
    }) {
        Box {
            if (isShowTitle) {
                Text(text = title, style = titleTextStyle, color = titleTextColor)
            }
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 6.dp)) {
                SavageTextField(
                    modifier = Modifier.weight(0.7f),
                    value = value,
                    hint = hint,
                    enable = isTextField,
                    onValueChange = onValueChange,
                    isHaveBottomLine = false,
                    keyboardActions = if (isTextField) {
                        KeyboardActions(
                            onDone = {
                                focusManager.clearFocus() // Clear focus when "Done" action is triggered
                            }
                        )
                    } else {
                        KeyboardActions()
                    },
                )
                Box(
                    modifier = Modifier
                        .background(Color.Transparent, RoundedCornerShape(8.dp))
                        .border(BorderStroke(1.dp, SavageColor.Black), RoundedCornerShape(80.dp))
                ) {
                    Text(
                        text = editBtnValue,
                        style = SavageTypography.Body1,
                        color = Color.Black,
                        modifier = Modifier.padding(
                            start = 12.dp,
                            end = 12.dp,
                            top = 6.dp,
                            bottom = 6.dp
                        )
                    )
                }
            }
        }
        Box {
            if (isShowSubTitle) {
                Text(text = subTitle, style = SavageTypography.Body2, color = Color(0xFF9DA3AF))
            }
        }
    }
}

@Preview
@Composable
fun PreviewSavageTextFieldWithEditBtn() {
    SavageTextFieldWithEditBtn(
        value = "010-1234-5678",
        title = "전화번호",
        titleTextStyle = TextStyle(),
        titleTextColor = Color(0xFF676F7E),
        hint = "전화번호를 입력해 주세요.",
        subTitle = "변경하시려면 변경 버튼을 눌러주세요.",
        onValueChange = {},
        isTextField = false,
    )
}
