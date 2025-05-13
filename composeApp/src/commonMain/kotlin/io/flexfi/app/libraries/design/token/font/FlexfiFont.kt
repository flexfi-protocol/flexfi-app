package io.flexfi.app.libraries.design.token.font

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import flexfi_app.composeapp.generated.resources.DaysOne_Regular
import flexfi_app.composeapp.generated.resources.Montserrat_Black
import flexfi_app.composeapp.generated.resources.Montserrat_BlackItalic
import flexfi_app.composeapp.generated.resources.Montserrat_Bold
import flexfi_app.composeapp.generated.resources.Montserrat_BoldItalic
import flexfi_app.composeapp.generated.resources.Montserrat_ExtraBold
import flexfi_app.composeapp.generated.resources.Montserrat_ExtraBoldItalic
import flexfi_app.composeapp.generated.resources.Montserrat_ExtraLight
import flexfi_app.composeapp.generated.resources.Montserrat_ExtraLightItalic
import flexfi_app.composeapp.generated.resources.Montserrat_Italic
import flexfi_app.composeapp.generated.resources.Montserrat_Light
import flexfi_app.composeapp.generated.resources.Montserrat_LightItalic
import flexfi_app.composeapp.generated.resources.Montserrat_Medium
import flexfi_app.composeapp.generated.resources.Montserrat_MediumItalic
import flexfi_app.composeapp.generated.resources.Montserrat_Regular
import flexfi_app.composeapp.generated.resources.Montserrat_SemiBold
import flexfi_app.composeapp.generated.resources.Montserrat_SemiBoldItalic
import flexfi_app.composeapp.generated.resources.Montserrat_Thin
import flexfi_app.composeapp.generated.resources.Montserrat_ThinItalic
import flexfi_app.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun MontserratFontFamily(): FontFamily {
    return FontFamily(
        Font(
            resource = Res.font.Montserrat_Black,
            weight = FontWeight.Black
        ),
        Font(
            resource = Res.font.Montserrat_BlackItalic,
            weight = FontWeight.Black,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.Montserrat_Bold,
            weight = FontWeight.Bold
        ),
        Font(
            resource = Res.font.Montserrat_BoldItalic,
            weight = FontWeight.Bold,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.Montserrat_ExtraBold,
            weight = FontWeight.ExtraBold
        ),
        Font(
            resource = Res.font.Montserrat_ExtraBoldItalic,
            weight = FontWeight.ExtraBold,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.Montserrat_ExtraLight,
            weight = FontWeight.ExtraLight
        ),
        Font(
            resource = Res.font.Montserrat_ExtraLightItalic,
            weight = FontWeight.ExtraLight,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.Montserrat_Italic,
            weight = FontWeight.Normal,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.Montserrat_Light,
            weight = FontWeight.Light
        ),
        Font(
            resource = Res.font.Montserrat_LightItalic,
            weight = FontWeight.Light,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.Montserrat_Medium,
            weight = FontWeight.Medium
        ),
        Font(
            resource = Res.font.Montserrat_MediumItalic,
            weight = FontWeight.Medium,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.Montserrat_Regular,
            weight = FontWeight.Normal
        ),
        Font(
            resource = Res.font.Montserrat_SemiBold,
            weight = FontWeight.SemiBold
        ),
        Font(
            resource = Res.font.Montserrat_SemiBoldItalic,
            weight = FontWeight.SemiBold,
            style = FontStyle.Italic
        ),
        Font(
            resource = Res.font.Montserrat_Thin,
            weight = FontWeight.Thin
        ),
        Font(
            resource = Res.font.Montserrat_ThinItalic,
            weight = FontWeight.Thin,
            style = FontStyle.Italic
        )
    )
}

@Composable
fun DaysOneFontFamily(): FontFamily {
    return FontFamily(
        Font(
            resource = Res.font.DaysOne_Regular,
            weight = FontWeight.Normal,
        )
    )
}
