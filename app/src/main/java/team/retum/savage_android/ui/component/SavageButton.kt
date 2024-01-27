package team.retum.savage_android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import team.retum.savage_android.ui.theme.SavageTypography

@Composable
fun SavageButton(
    modifier: Modifier = Modifier,
    text: String = "default",
    onClick: () -> Unit,
    isAbleClick: Boolean = false,
) {
    var isPressed by remember { mutableStateOf(false) }

    val colors = ButtonDefaults.buttonColors(
        containerColor = when {
            isAbleClick -> if (isPressed) Color(0xFF1E864A) else Color(0xFF27AF62)
            else -> if (isPressed) Color(0xFF1E864A) else Color(0xFFB4EECD)
        },
        contentColor = Color.White,
        disabledContainerColor = Color(0xFFB4EECD),
        disabledContentColor = Color(0xFFFFFFFF),
    )

    val interactionSource = remember { MutableInteractionSource() }
    val isPressedState by interactionSource.collectIsPressedAsState()

    LaunchedEffect(isPressedState) {
        isPressed = isPressedState
    }

    Button(
        onClick = {
            onClick()
        },
        enabled = isAbleClick,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(4.dp),
        colors = colors,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(horizontal = 4.dp)
    ) {
        Text(text = text, style = SavageTypography.Body2)
    }
}

@Preview
@Composable
fun SavageButtonPreview() {
    SavageButton(
        text = "Savage Button",
        onClick = { println("Savage Button Clicked") },
        isAbleClick = false
    )
}
