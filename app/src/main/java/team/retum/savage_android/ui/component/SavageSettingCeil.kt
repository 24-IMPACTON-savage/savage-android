package team.retum.savage_android.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import team.retum.savage_android.ui.theme.SavageColor
import team.retum.savage_android.ui.theme.SavageTypography

@Composable
fun SavageSettingCeil(
    title: String,
    isFrontArrow: Boolean = false,
    desc: String
//    callback: () -> Unit
) {
    Row(
        modifier=Modifier.padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Text(text = title, style = SavageTypography.Body2)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = desc, style = SavageTypography.Body2, color = SavageColor.Gray20)
        if (isFrontArrow) {
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
    }
}