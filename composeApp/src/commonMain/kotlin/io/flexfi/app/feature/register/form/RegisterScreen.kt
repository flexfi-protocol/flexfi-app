package io.flexfi.app.feature.register.form

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import io.flexfi.app.libraries.design.component.button.ButtonAccent
import io.flexfi.app.libraries.design.component.checkbox.FlexfiCheckbox
import io.flexfi.app.libraries.design.component.spacer.VerticalSpacer
import io.flexfi.app.libraries.design.component.textfield.NeonTextField
import io.flexfi.app.libraries.design.token.color.FlexfiColors
import io.flexfi.app.libraries.design.token.font.DaysOneFontFamily
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterScreen(
    onNavToCongratulation: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(state = State.RESUMED) {
            viewModel.uiEvents.collect { event ->
                when (event) {
                    UiEvent.StartCongratulationUi -> onNavToCongratulation()
                }
            }
        }
    }

    RegisterScreen(
        modifier = modifier,
        state = state,
        onFirstNameChange = viewModel::onFirstNameChange,
        onLastNameChange = viewModel::onLastNameChange,
        onEmailChange = viewModel::onEmailChange,
        onReferralCodeChange = viewModel::onReferralCodeChange,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
        onGdprCheckChange = viewModel::onGdprAcceptedChange,
        onPrivacyPolicyCheckChange = viewModel::onPrivacyPolicyAcceptedChange,
        onRegisterClick = viewModel::onRegisterButtonClick,
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
    onGdprCheckChange: () -> Unit,
    onPrivacyPolicyCheckChange: () -> Unit,
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
                    .padding(horizontal = 24.dp)
                    .padding(top = 16.dp)
                    .onSizeChanged { titleHeightDp = density.run { it.height.toDp() } },
                text = "Create your\naccount",
                fontFamily = DaysOneFontFamily(),
                fontSize = 30.sp,
                lineHeight = 34.sp,
                color = Color.White,
            )

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .padding(
                        bottom = paddingValues.calculateBottomPadding(),
                        top = titleHeightDp + 24.dp + 16.dp,
                    )
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
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        NeonTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.email,
                            onValueChange = onEmailChange,
                            label = "Email",
                            placeholder = "you@example.com",
                            required = true,
                            keyboardType = KeyboardType.Email,
                            error = state.emailError,
                        )

                        NeonTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.firstName,
                            onValueChange = onFirstNameChange,
                            label = "First Name",
                            placeholder = "Your first name",
                            required = true,
                            error = state.firstNameError,
                        )

                        NeonTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.lastName,
                            onValueChange = onLastNameChange,
                            label = "Last Name",
                            placeholder = "Your last name",
                            required = true,
                            error = state.lastNameError,
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
                            error = state.passwordError,
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
                            error = state.confirmPasswordError,
                        )

                        VerticalSpacer(4.dp)

                        FlexfiCheckbox(
                            label = "I agree to receive marketing emails (GDPR)",
                            checked = state.gdprAccepted,
                            onCheckedChange = { onGdprCheckChange() },
                        )

                        FlexfiCheckbox(
                            label = "I accept the Privacy Policy",
                            checked = state.privacyPolicyAccepted,
                            onCheckedChange = { onPrivacyPolicyCheckChange() },
                        )

                        VerticalSpacer(4.dp)

                        ButtonAccent(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            text = "Register",
                            loading = state.loading,
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