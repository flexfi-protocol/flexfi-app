package io.flexfi.app

import androidx.compose.ui.window.ComposeUIViewController
import io.flexfi.app.core.injection.initKoin

fun MainViewController() =
    ComposeUIViewController(
        configure = {
            initKoin()
        }
    ) { App() }