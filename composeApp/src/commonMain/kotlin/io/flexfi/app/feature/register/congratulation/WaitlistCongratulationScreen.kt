package io.flexfi.app.feature.register.congratulation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.flexfi.app.libraries.design.component.spacer.VerticalSpacer
import io.flexfi.app.libraries.design.token.color.FlexfiColors
import io.flexfi.app.libraries.design.token.font.MontserratFontFamily
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WaitlistCongratulationScreen(
    modifier: Modifier = Modifier,
    viewModel: WaitlistCongratulationViewModel = koinViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    WaitlistCongratulationScreen(
        modifier = modifier,
        state = state.value,
    )
}

@Composable
private fun WaitlistCongratulationScreen(
    state: UiState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        containerColor = Color.Transparent,
        bottomBar = { },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to FlexfiColors.darkBlue,
                            0.5f to FlexfiColors.darkBlue,
                            1.0f to FlexfiColors.lightBlueGrey,
                        )
                    )
                )
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
        ) {
            VerticalSpacer(24.dp)

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("Congratulations ")
                    }
                    withStyle(style = SpanStyle(color = FlexfiColors.lightBlue)) {
                        append(state.name)
                    }
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append(",\nyou’re in!")
                    }
                },
                color = Color.White,
                fontSize = 30.sp,
                lineHeight = 34.sp,
                fontFamily = MontserratFontFamily(),
                fontWeight = FontWeight.Bold,
            )

            Spacer(Modifier.weight(.3f))

            Column(
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append("Your referral code: ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = FlexfiColors.lightBlue,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(state.referralCode.uppercase())
                        }
                    },
                    color = Color.White,
                    fontSize = 18.sp,
                    lineHeight = 20.sp,
                    fontFamily = MontserratFontFamily(),
                )

                VerticalSpacer(24.dp)

                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = buildAnnotatedString {
                        append("You are user ")
                        withStyle(
                            style = SpanStyle(fontWeight = FontWeight.Bold)
                        ) { append("#${state.positionInWaitlist}") }
                        append(" on the waitlist")
                    },
                    color = FlexfiColors.lightBlue,
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontFamily = MontserratFontFamily(),
                )

            }

            Spacer(Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
private fun Preview_WaitlistCongratulationScreen() {
    MaterialTheme {
        WaitlistCongratulationScreen(
            state = UiState(
                name = "Sonny",
                referralCode = "flex-u6rndk",
                positionInWaitlist = 22,
            )
        )
    }
}
