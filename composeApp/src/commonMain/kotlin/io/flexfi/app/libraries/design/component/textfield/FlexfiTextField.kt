package io.flexfi.app.libraries.design.component.textfield

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.flexfi.app.libraries.design.token.color.FlexfiColors
import io.flexfi.app.libraries.design.token.font.MontserratFontFamily
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NeonTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    required: Boolean,
    modifier: Modifier = Modifier,
    keyboardImeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label + if (required) " *" else "",
                fontFamily = MontserratFontFamily(),
                fontSize = 14.sp,
                color = FlexfiColors.lightBlue,
                maxLines = 1,
            )
        },
        placeholder = { Text(text = placeholder) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = FlexfiColors.lightBlue,
            focusedLabelColor = FlexfiColors.lightBlue,
            focusedPlaceholderColor = Color.White.copy(alpha = .3f),
            unfocusedPlaceholderColor = Color.White.copy(alpha = .3f),
            focusedBorderColor = FlexfiColors.lightBlue,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = keyboardImeAction,
            keyboardType = keyboardType,
        ),
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(16.dp),
    )
}

@Preview
@Composable
private fun Preview_FlexfiTextField() {
    NeonTextField(
        value = "",
        onValueChange = {},
        label = "Email",
        placeholder = "Placeholder",
        required = true,
    )
}
