/*
 * Quotebook
 *
 * Created by artembirmin on 18/9/2023.
 */

package com.incetro.quotebook.presentation.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

val md_theme_light_primary = Color(0xFF6750A4)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFEADDFF)
val md_theme_light_onPrimaryContainer = Color(0xFF21005D)
val md_theme_light_secondary = Color(0xFF625B71)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFE8DEF8)
val md_theme_light_onSecondaryContainer = Color(0xFF1D192B)
val md_theme_light_tertiary = Color(0xFF7D5260)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFFFD8E4)
val md_theme_light_onTertiaryContainer = Color(0xFF31111D)
val md_theme_light_error = Color(0xFFB3261E)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_errorContainer = Color(0xFFF9DEDC)
val md_theme_light_onErrorContainer = Color(0xFF410E0B)
val md_theme_light_outline = Color(0xFF79747E)
val md_theme_light_background = Color(0xFFFFFBFE)
val md_theme_light_onBackground = Color(0xFF1C1B1F)
val md_theme_light_surface = Color(0xFFFFFBFE)
val md_theme_light_onSurface = Color(0xFF1C1B1F)
val md_theme_light_surfaceVariant = Color(0xFFE7E0EC)
val md_theme_light_onSurfaceVariant = Color(0xFF49454F)
val md_theme_light_inverseSurface = Color(0xFF313033)
val md_theme_light_inverseOnSurface = Color(0xFFF4EFF4)
val md_theme_light_inversePrimary = Color(0xFFD0BCFF)
val md_theme_light_shadow = Color(0xFF000000)
val md_theme_light_surfaceTint = Color(0xFF6750A4)
val md_theme_light_outlineVariant = Color(0xFFCAC4D0)
val md_theme_light_scrim = Color(0xFF000000)

val md_theme_dark_primary = Color(0xFFD0BCFF)
val md_theme_dark_onPrimary = Color(0xFF381E72)
val md_theme_dark_primaryContainer = Color(0xFF4F378B)
val md_theme_dark_onPrimaryContainer = Color(0xFFEADDFF)
val md_theme_dark_secondary = Color(0xFFCCC2DC)
val md_theme_dark_onSecondary = Color(0xFF332D41)
val md_theme_dark_secondaryContainer = Color(0xFF4A4458)
val md_theme_dark_onSecondaryContainer = Color(0xFFE8DEF8)
val md_theme_dark_tertiary = Color(0xFFEFB8C8)
val md_theme_dark_onTertiary = Color(0xFF492532)
val md_theme_dark_tertiaryContainer = Color(0xFF633B48)
val md_theme_dark_onTertiaryContainer = Color(0xFFFFD8E4)
val md_theme_dark_error = Color(0xFFF2B8B5)
val md_theme_dark_onError = Color(0xFF601410)
val md_theme_dark_errorContainer = Color(0xFF8C1D18)
val md_theme_dark_onErrorContainer = Color(0xFFF9DEDC)
val md_theme_dark_outline = Color(0xFF938F99)
val md_theme_dark_background = Color(0xFF1C1B1F)
val md_theme_dark_onBackground = Color(0xFFE6E1E5)
val md_theme_dark_surface = Color(0xFF1C1B1F)
val md_theme_dark_onSurface = Color(0xFFE6E1E5)
val md_theme_dark_surfaceVariant = Color(0xFF49454F)
val md_theme_dark_onSurfaceVariant = Color(0xFFCAC4D0)
val md_theme_dark_inverseSurface = Color(0xFFE6E1E5)
val md_theme_dark_inverseOnSurface = Color(0xFF313033)
val md_theme_dark_inversePrimary = Color(0xFF6750A4)
val md_theme_dark_shadow = Color(0xFF000000)
val md_theme_dark_surfaceTint = Color(0xFFD0BCFF)
val md_theme_dark_outlineVariant = Color(0xFF49454F)
val md_theme_dark_scrim = Color(0xFF000000)

val quoteBackgroundBrushesLightColors = mutableMapOf(
    1 to listOf(Color(0xFFEF9A9A), Color(0xFFE57373)),
    2 to listOf(Color(0xFFF48FB1), Color(0xFFF06292)),
    3 to listOf(Color(0xFFCE93D8), Color(0xFFBA68C8)),
    4 to listOf(Color(0xFFB39DDB), Color(0xFF9575CD)),
    5 to listOf(Color(0xFF9FA8DA), Color(0xFF7986CB)),
    6 to listOf(Color(0xFF90CAF9), Color(0xFF64B5F6)),
    7 to listOf(Color(0xFF81D4FA), Color(0xFF4FC3F7)),
    8 to listOf(Color(0xFF80DEEA), Color(0xFF4DD0E1)),
).mapValues { entry -> entry.value.map { it.copy(alpha = 0.5f) } }

val quoteBackgroundBrushesLight = QuoteBackgroundBrushes(
    quoteBackgroundBrushesLightColors
        .mapValues { entry -> Brush.linearGradient(entry.value) }
)

val quoteBackgroundBrushesDark = QuoteBackgroundBrushes(
    quoteBackgroundBrushesLightColors
        .mapValues { entry ->
            Brush.linearGradient(
                entry.value.toMutableList().map { it.darker() })
        }
)

fun Color.darker(ratio: Float = 0.7f): Color = Color(
    ColorUtils.blendARGB(
        this.toArgb(),
        Color.Black.toArgb(),
        ratio
    )
)