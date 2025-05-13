package io.flexfi.app.libraries.design.component.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.flexfi.app.libraries.design.token.color.FlexfiColors
import io.flexfi.app.libraries.design.token.font.MontserratFontFamily
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ButtonAccent(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FilledTonalButton(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = FlexfiColors.mediumBlue,
            contentColor = FlexfiColors.darkBlue,
        )
    ) {
        Text(
            text = text,
            fontFamily = MontserratFontFamily(),
            fontSize = 16.sp,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
private fun Preview_ButtonAccent() {
    MaterialTheme {
        ButtonAccent(
            text = "Register",
            onClick = {},
        )
    }
}
