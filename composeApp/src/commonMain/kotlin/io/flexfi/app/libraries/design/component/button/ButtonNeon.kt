package io.flexfi.app.libraries.design.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.flexfi.app.libraries.design.token.color.FlexfiColors
import io.flexfi.app.libraries.design.token.font.MontserratFontFamily
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ButtonNeon(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    neonColor: Color = FlexfiColors.lightBlue,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(
            width = 1.dp,
            color = neonColor,
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = text,
            fontFamily = MontserratFontFamily(),
            fontSize = 14.sp,
            maxLines = 1,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
private fun Preview_NeonButton() {
    MaterialTheme {
        ButtonNeon(
            text = "Join Waitlist",
            onClick = {},
        )
    }
}
