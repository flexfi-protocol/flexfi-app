package io.flexfi.app.libraries.design.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    loading: Boolean = false,
) {
    FilledTonalButton(
        modifier = modifier,
        onClick = {
            if (!loading) {
                onClick()
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = FlexfiColors.mediumBlue,
            contentColor = FlexfiColors.darkBlue,
        )
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                strokeWidth = 3.dp,
                color = FlexfiColors.darkBlue,
            )
        } else {
            Text(
                text = text,
                fontFamily = MontserratFontFamily(),
                fontSize = 16.sp,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview
@Composable
private fun Preview_ButtonAccent() {
    MaterialTheme {
        var loading by remember { mutableStateOf(false) }

        ButtonAccent(
            modifier = Modifier.fillMaxWidth(),
            text = "Register",
            loading = loading,
            onClick = {
                loading = !loading
            },
        )
    }
}
