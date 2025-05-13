package io.flexfi.app.feature.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.flexfi.app.feature.register.state.UiState
import io.flexfi.app.libraries.design.component.button.ButtonAccent
import io.flexfi.app.libraries.design.component.checkbox.FlexfiCheckbox
import io.flexfi.app.libraries.design.component.spacer.VerticalSpacer
import io.flexfi.app.libraries.design.component.textfield.NeonTextField
import io.flexfi.app.libraries.design.token.color.FlexfiColors
import io.flexfi.app.libraries.design.token.font.DaysOneFontFamily
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
) {
    RegisterScreen(
        modifier = modifier,
        state = UiState(),
        onFirstNameChange = {},
        onLastNameChange = {},
        onEmailChange = {},
        onReferralCodeChange = {},
        onPasswordChange = {},
        onConfirmPasswordChange = {},
        onGdprCheckChange = {},
        onPrivacyPolicyCheckChange = {},
        onRegisterClick = {},
    )
}

@Composable
private fun RegisterScreen(
    state: UiState,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onReferralCodeChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onGdprCheckChange: (Boolean) -> Unit,
    onPrivacyPolicyCheckChange: (Boolean) -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        containerColor = Color.Transparent,
        bottomBar = { },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to FlexfiColors.darkBlue,
                            0.5f to FlexfiColors.darkBlue,
                            1.0f to FlexfiColors.lightBlue
                        )
                    )
                )
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            val density = LocalDensity.current
            var titleHeightDp by remember { mutableStateOf(0.dp) }

            Text(
                modifier = Modifier
                    .padding(vertical = 32.dp, horizontal = 24.dp)
                    .onGloballyPositioned {
                        titleHeightDp = density.run { it.size.height.toDp() }
                    },
                text = "Create your\naccount",
                fontFamily = DaysOneFontFamily(),
                fontSize = 30.sp,
                lineHeight = 32.sp,
                color = Color.White,
            )

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(top = titleHeightDp + 32.dp)
                    .padding(horizontal = 24.dp),
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = FlexfiColors.blueGrey,
                    shape = RoundedCornerShape(32.dp),
                    border = BorderStroke(1.dp, FlexfiColors.lightBlueGrey),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        NeonTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.email,
                            onValueChange = onEmailChange,
                            label = "Email",
                            placeholder = "you@example.com",
                            required = true,
                            keyboardType = KeyboardType.Email,
                        )

                        NeonTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.firstName,
                            onValueChange = onFirstNameChange,
                            label = "First Name",
                            placeholder = "Your first name",
                            required = true,
                        )

                        NeonTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.lastName,
                            onValueChange = onLastNameChange,
                            label = "Last Name",
                            placeholder = "Your last name",
                            required = true,
                        )

                        NeonTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.referralCode,
                            onValueChange = onReferralCodeChange,
                            label = "Referral Code",
                            placeholder = "Your referral code",
                            required = false,
                        )

                        NeonTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.password,
                            onValueChange = onPasswordChange,
                            label = "Password",
                            placeholder = "Your password",
                            required = true,
                            visualTransformation = PasswordVisualTransformation(),
                        )

                        NeonTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.confirmPassword,
                            onValueChange = onConfirmPasswordChange,
                            label = "Confirm Password",
                            placeholder = "Your password again",
                            required = true,
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardImeAction = ImeAction.Done,
                        )

                        VerticalSpacer(4.dp)

                        FlexfiCheckbox(
                            label = "I agree to receive marketing emails (GDPR)",
                            checked = state.gdprAccepted,
                            onCheckedChange = onGdprCheckChange,
                        )

                        FlexfiCheckbox(
                            label = "I accept the Privacy Policy",
                            checked = state.privacyPolicyAccepted,
                            onCheckedChange = onPrivacyPolicyCheckChange,
                        )

                        VerticalSpacer(4.dp)

                        ButtonAccent(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            text = "Register",
                            onClick = {
                                onRegisterClick()
                            },
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview_RegisterScreen() {
    RegisterScreen(
        modifier = Modifier.fillMaxSize(),
        state = UiState(),
        onFirstNameChange = {},
        onLastNameChange = {},
        onEmailChange = {},
        onReferralCodeChange = {},
        onPasswordChange = {},
        onConfirmPasswordChange = {},
        onGdprCheckChange = {},
        onPrivacyPolicyCheckChange = {},
        onRegisterClick = {},
    )
}