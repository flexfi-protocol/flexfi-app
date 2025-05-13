package io.flexfi.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.flexfi.app.feature.home.HomeScreen
import io.flexfi.app.feature.register.congratulation.WaitlistCongratulationScreen
import io.flexfi.app.feature.register.form.RegisterScreen
import io.flexfi.app.libraries.navigation.Screen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screen.Home,
        ) {
            composable<Screen.Home> {
                HomeScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNavToRegister = { navController.navigate(Screen.Register) }
                )
            }

            composable<Screen.Register> {
                RegisterScreen(modifier = Modifier.fillMaxSize())
            }

            composable<Screen.WaitlistCongratulation> {
                WaitlistCongratulationScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
