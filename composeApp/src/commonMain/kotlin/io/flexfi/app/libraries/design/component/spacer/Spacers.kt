package io.flexfi.app.libraries.design.component.spacer

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun ColumnScope.VerticalSpacer(height: Dp) {
    Spacer(Modifier.height(height))
}

@Composable
fun RowScope.HorizontalSpacer(width: Dp) {
    Spacer(Modifier.width(width))
}
