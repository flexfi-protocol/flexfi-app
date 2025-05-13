package io.flexfi.app.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.flexfi.app.feature.home.component.RocketLottie
import io.flexfi.app.libraries.design.component.button.ButtonNeon
import io.flexfi.app.libraries.design.component.spacer.VerticalSpacer
import io.flexfi.app.libraries.design.token.color.FlexfiColors
import io.flexfi.app.libraries.design.token.font.DaysOneFontFamily
import io.flexfi.app.libraries.design.token.font.MontserratFontFamily
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    onNavToRegister: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var animateLaunch by remember { mutableStateOf(false) }

    HomeScreen(
        modifier = modifier,
        animateLaunch = animateLaunch,
        onJoinWaitlistClick = { animateLaunch = true },
        onAnimationEnd = { onNavToRegister() }
    )
}

@Composable
private fun HomeScreen(
    animateLaunch: Boolean,
    onJoinWaitlistClick: () -> Unit,
    onAnimationEnd: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val haptic = LocalHapticFeedback.current

    Scaffold(
        modifier = modifier,
        containerColor = FlexfiColors.darkBlue,
        bottomBar = { },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
        ) {
            VerticalSpacer(16.dp)

            AnimatedVisibility(
                visible = !animateLaunch,
                exit = fadeOut(animationSpec = tween(delayMillis = 600))
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append("Join the\nFlexFi Founders\n")
                        }
                        withStyle(style = SpanStyle(color = FlexfiColors.lightBlue)) {
                            append("Tribe")
                        }
                    },
                    fontFamily = DaysOneFontFamily(),
                    fontSize = 30.sp,
                    lineHeight = 32.sp
                )
            }

            VerticalSpacer(8.dp)

            AnimatedVisibility(
                visible = !animateLaunch,
                exit = fadeOut(animationSpec = tween(delayMillis = 500))
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append("Get")
                        }
                        withStyle(style = SpanStyle(color = FlexfiColors.lightBlue)) {
                            append(" early access")
                        }
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append(", exclusive rewards, and\nyour place in the Hall of Fame")
                        }
                    },
                    fontFamily = MontserratFontFamily(),
                    fontSize = 14.sp,
                    lineHeight = 16.sp
                )
            }

            Spacer(Modifier.weight(1f))

            val rocketHeight = 200.dp
            val density = LocalDensity.current
            var launchingPadHeightPx by remember { mutableIntStateOf(0) }
            val rocketOffsetDp by animateDpAsState(
                targetValue = if (animateLaunch) {
                    density.run { -launchingPadHeightPx.toDp() - rocketHeight }
                } else {
                    0.dp
                },
                animationSpec = tween(
                    durationMillis = 2_000,
                    delayMillis = 200,
                    easing = CubicBezierEasing(.78f, -0.40f, .22f, 1.22f)
                ),
                finishedListener = { onAnimationEnd() }
            )
            val rocketAlpha by animateFloatAsState(
                targetValue = if (animateLaunch) 0f else 1f,
                animationSpec = tween(
                    durationMillis = 1_000,
                    delayMillis = 1_000,
                ),
            )

            Column(
                modifier = Modifier.fillMaxWidth()
                    .onGloballyPositioned { launchingPadHeightPx = it.size.height },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                RocketLottie(
                    Modifier
                        .size(200.dp)
                        .align(Alignment.CenterHorizontally)
                        .offset(y = rocketOffsetDp)
                        .graphicsLayer { alpha = rocketAlpha },
                )

                VerticalSpacer(24.dp)

                val buttonAlpha by animateFloatAsState(
                    targetValue = if (animateLaunch) 0f else 1f,
                    animationSpec = tween(delayMillis = 500),
                )

                ButtonNeon(
                    modifier = Modifier.graphicsLayer {
                        alpha = buttonAlpha
                    },
                    text = "Join Waitlist",
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onJoinWaitlistClick()
                    },
                )

                VerticalSpacer(24.dp)
            }
        }
    }
}

@Preview
@Composable
private fun Preview_RegisterScreen() {
    MaterialTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            animateLaunch = false,
            onJoinWaitlistClick = {},
            onAnimationEnd = {},
        )
    }
}
