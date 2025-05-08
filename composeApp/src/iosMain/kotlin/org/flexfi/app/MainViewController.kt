package org.flexfi.app

import androidx.compose.ui.window.ComposeUIViewController
import org.flexfi.app.core.injection.initKoin

fun MainViewController() =
    ComposeUIViewController(
        configure = {
            initKoin()
        }
    ) { App() }