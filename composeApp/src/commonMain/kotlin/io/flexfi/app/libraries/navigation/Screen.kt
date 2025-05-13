package io.flexfi.app.libraries.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable object Home : Screen()
    @Serializable object Register : Screen()
    @Serializable object WaitlistCongratulation : Screen()
}
