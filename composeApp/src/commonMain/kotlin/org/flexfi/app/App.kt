package org.flexfi.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.flexfi.app.core.domain.session.SessionRepository
import org.flexfi.app.libraries.result.successOrThrow
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    MaterialTheme {
        val repository: SessionRepository = koinInject()

        val scope = rememberCoroutineScope()

        var referralCode by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = {
                    scope.launch {
                        val response =
                            repository.register(
                                email = "melwin.magalhaes+test2@gmail.com",
                                password = "12345678",
                                firstName = "Melwin",
                                lastName = "Magalhaes",
                                referralCodeUsed = null,
                            ).successOrThrow()

                        println("Register response: $response")
                        referralCode = response.user.userReferralCode ?: ""
                    }
                }
            ) {
                Text("Click me!")
            }

            Spacer(Modifier.height(48.dp))

            Text(
                text = "Referral code: ${referralCode.uppercase()}",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Blue,
            )
        }
    }
}
